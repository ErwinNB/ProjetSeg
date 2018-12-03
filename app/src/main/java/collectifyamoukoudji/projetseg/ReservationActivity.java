package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CalendarView;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ReservationActivity extends AppCompatActivity {

    private CalendarView calendar;
    private ArrayList<String> heures;
    private Button enr;
    private String iduser;
    private DatabaseReference databaseUsers;
    private DatabaseReference reference;
    private ArrayList<String> selected;
    private CheckBox[] checkBoxes;
    private CheckBox b1, b2, b3, b4, b5, b6;
    private CheckBox b7, b8, b9, b10, b11, b12;
    private String cUser;
    private String fUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        cUser = getIntent().getStringExtra("iduser");
        fUser = getIntent().getStringExtra("idUser");
        setupUI();

        enr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrer();
            }
        });
    }


    private void setupUI(){
        calendar = (CalendarView)findViewById(R.id.calendar);
        long currentDateTime = System.currentTimeMillis();
        calendar.setMinDate(currentDateTime);

        /*Date currentDate = new Date(currentDateTime);
        DateFormat df = new SimpleDateFormat("dd:MM:yy");*/

      //  long nbre = date.toDateTimeAtCurrentTime().getMillis();
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("A");
        long nombre =  Long.parseLong(date.format(formatter));
        */

        heures = new ArrayList();
        heures.add("8 - 9h");
        heures.add("9 - 10h");
        heures.add("10 - 11h");
        heures.add("11 - 12h");
        heures.add("12 - 13h");
        heures.add("13 - 14h");
        heures.add("14 - 15h");
        heures.add("15 - 16h");
        heures.add("16 - 17h");
        heures.add("17 - 18h");
        heures.add("18 - 19h");
        heures.add("19 - 20h");

        checkBoxes = new CheckBox[12];
        b1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBoxes[1] = b1;
        b2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBoxes[2] = b2;
        b3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBoxes[3] = b3;
        b4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBoxes[4] = b4;
        b5 = (CheckBox)findViewById(R.id.checkBox5);
        checkBoxes[5] = b5;
        b6 = (CheckBox)findViewById(R.id.checkBox6);
        checkBoxes[6] = b6;
        b7 = (CheckBox)findViewById(R.id.checkBox7);
        checkBoxes[7] = b7;
        b8 = (CheckBox)findViewById(R.id.checkBox8);
        checkBoxes[8] = b8;
        b9 = (CheckBox)findViewById(R.id.checkBox9);
        checkBoxes[9] = b9;
        b10 = (CheckBox)findViewById(R.id.checkBox10);
        checkBoxes[10] = b10;
        b11 = (CheckBox)findViewById(R.id.checkBox11);
        checkBoxes[11] = b11;
        b12 = (CheckBox)findViewById(R.id.checkBox12);
        checkBoxes[12] = b12;
        
        enr = (Button)findViewById(R.id.enregistrer);
    }

    public void enregistrer(){
        Long date = calendar.getDate();
        Date selectedDate = new Date(date);

        selected = new ArrayList<String>();
        boolean check = false;
        for (int i = 1; i <= 12; i++ )
        {
            if (checkBoxes[i].isChecked()) {
                selected.add(heures.get(i));
                check = true;
            }
        }

        if (cUser != null && fUser != null){

            if (check == true){
               
            }
        }

    }
}
