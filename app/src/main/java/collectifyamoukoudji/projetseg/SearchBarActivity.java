package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchBarActivity extends AppCompatActivity {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> serviceList;
    ArrayList<Double> rateList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        /*
        * Create a array list for each node you want to use
        * */
        serviceList = new ArrayList<>();
        rateList = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                    * Clear the list when editText is empty
                    * */
                    serviceList.clear();
                    rateList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("Services").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                * Clear the list for every new search
                * */
                serviceList.clear();
                rateList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                * Search all users for matching searched string
                * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String service_name = snapshot.child("serviceName").getValue(String.class);
                    Double rate = snapshot.child("rate").getValue(Double.class);

                    if (service_name.toLowerCase().contains(searchedString.toLowerCase())) {
                        serviceList.add(service_name);
                        rateList.add(rate);
                        counter++;
                    }  else if (String.valueOf(rate).toLowerCase().contains(searchedString.toLowerCase())) {
                        serviceList.add(service_name);
                        rateList.add(rate);
                        counter++;
                    }

                    /*
                    * Get maximum of 15 searched results only
                    * */
                    if (counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(SearchBarActivity.this, serviceList, rateList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
