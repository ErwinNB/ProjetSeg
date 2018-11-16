package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OrganisationFragment extends Fragment{

    View myView;

    private ArrayList<String> ServiceOffert;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private ArrayAdapter<String> ListArrayAdapter;
    private DatabaseReference databaseService;
    private Button btnAjouter;
    private ListView listServices;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.organisation_layout, container, false);

        setupUI();

        //fill the spinner with the Db

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        listServices.setAdapter(ListArrayAdapter);
        //load services from DB
        loadEntries();
        //add services to th list of services
        btnAjouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String services = (String)spinner.getSelectedItem();
                ServiceOffert.add(services);
                spinnerArrayAdapter.remove(services);
                ListArrayAdapter.notifyDataSetChanged();
                spinnerArrayAdapter.notifyDataSetChanged();

            }
        });
        return myView;
    }

    private void setupUI() {
        ServiceOffert = new ArrayList<>();
        databaseService = FirebaseDatabase.getInstance().getReference("Services");
        btnAjouter = (Button) myView.findViewById(R.id.buttonAddService);
        listServices = (ListView)myView.findViewById(R.id.listViewSvcOff);
        spinner = (Spinner) myView.findViewById(R.id.ServicesOffert);
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.spinner_item);
        ListArrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, ServiceOffert);
    }

    private void loadEntries() {
        databaseService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Service value = postsnapshot.getValue(Service.class);
                    spinnerArrayAdapter.add(value.getServiceName());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

            }
        });
    }



    }


