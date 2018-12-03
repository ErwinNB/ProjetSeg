package collectifyamoukoudji.projetseg;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sDays;
    private Spinner sTimeslt;
    private TextView org_name;
    private TextView client_name;
    private TextView booking_id;
    private Button buttonBook;
    ArrayAdapter<String> spinnerArrayAdapter;
    int index;
    String today;

    private ArrayList<String> days;
    private ArrayList<String> times;

    private String iduser;
    private String clientName;
    private String orgName;
    private DatabaseReference rateReference;

    List<String> list = new ArrayList<String>();
    List<String> listd = new ArrayList<String>();
    List<String> list2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        iduser = getIntent().getStringExtra("idUser");
        clientName = getIntent().getStringExtra("client");
        orgName =  getIntent().getStringExtra("orgName");

        setupUI();

        loadDays();

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toastMessage(list.toString());
            }
        });


    }

    private void loadDays(){

        rateReference = FirebaseDatabase.getInstance().getReference("Users").child(iduser).child("_currentOrganisation").child("_organisationHorraire");

        rateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 booking_id.setText(rateReference.push().getKey());

                Horraire value = dataSnapshot.getValue(Horraire.class);

                ArrayList<ArrayList<Boolean>> arr = value.get_array();

                for (int i = 0; i < 12 ; i++) {
                    for (int j = 0; j < 7; j++) {
                        if(arr.get(i).get(j).equals(true)){
                            list.add(days.get(j)+" "+times.get(i));
                            spinnerArrayAdapter.add(days.get(j)+" "+times.get(i));

                        }

                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void setupUI(){

        days = new ArrayList<>();
        times = new ArrayList<>();

        days.add("LUN");
        days.add("MAR");
        days.add("MER");
        days.add("JED");
        days.add("VEN");
        days.add("SAM");
        days.add("DIM");

        times.add("8-9h");
        times.add("9-10h");
        times.add("10-11h");
        times.add("11-12h");
        times.add("12-13h");
        times.add("13-14h");
        times.add("14-15h");
        times.add("15-16h");
        times.add("16-17h");
        times.add("17-18h");
        times.add("18-19h");
        times.add("19-20h");

        org_name = (TextView)findViewById(R.id.textViewNomOrg);
        client_name = (TextView)findViewById(R.id.textViewClient);
        booking_id = (TextView)findViewById(R.id.textBookingid);
         sTimeslt = (Spinner)findViewById(R.id.spinnerTimeSlt);
         sDays = (Spinner)findViewById(R.id.spinnerDays);
         buttonBook = (Button)findViewById(R.id.buttonBooking);
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        sDays.setOnItemSelectedListener(this);
        sDays.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter.add("Days");

        org_name.setText(orgName);
        client_name.setText(clientName);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sp1= String.valueOf(sDays.getSelectedItem());
        Toast.makeText(this, sp1, Toast.LENGTH_SHORT).show();

        if(sp1.contains("Days")) {
            list2.clear();
            toastMessage("Chose an option");
        }
        if(sp1.contains("LUN")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().format(new Date());
            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            ArrayList<String> days = new ArrayList<>(31);
            for (int i = 0; i < 31; i++)
            {
                days.add(format.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            for (String str : days){
                Log.d("ARR", str);
                if (str.equals(today)){
                    index = days.indexOf(today);
                }
            }



            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.size(); i+=7){
                list2.add(days.get(i));
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }
        if(sp1.contains("MAR")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }
        if(sp1.contains("MER")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }
        if(sp1.contains("JED")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }
        if(sp1.contains("VEN")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }
        if(sp1.contains("SAM")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);
        }

        if(sp1.contains("DIM")) {

            list2.clear();

            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            Calendar calendar = Calendar.getInstance();
            today = java.text.DateFormat.getDateInstance().
                    format(new Date());

            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

            String[] days = new String[31];
            for (int i = 0; i < 31; i++)
            {
                days[i] = format.format(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }


            Log.d("TODAY", today);

            Log.d("ARR", days.toString());


            for (int i = 0; i < days.length; i++){
                if (days[i].equals(today)){
                    index = i;
                }
            }
            Log.d("INDEX", String.valueOf(index));

            for (int i = index; i < days.length; i+=7){
                list2.add(days[i]);
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list2);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            sTimeslt.setAdapter(dataAdapter);

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
