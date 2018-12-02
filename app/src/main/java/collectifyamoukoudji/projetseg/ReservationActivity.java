package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CalendarView;
import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    private CalendarView calendar;
    private ArrayList<String> times;
    private Button enr;
    private String iduser;
    private DatabaseReference databaseUsers;
    private DatabaseReference rateReference;
    private CheckBox b1, b2, b3, b4, b5, b6;
    private CheckBox b7, b8, b9, b10, b11, b12;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        iduser = getIntent().getStringExtra("idUser");
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
        b1 = (CheckBox)findViewById(R.id.checkBox1);
        b2 = (CheckBox)findViewById(R.id.checkBox2);
        b3 = (CheckBox)findViewById(R.id.checkBox3);
        b4 = (CheckBox)findViewById(R.id.checkBox4);
        b5 = (CheckBox)findViewById(R.id.checkBox5);
        b6 = (CheckBox)findViewById(R.id.checkBox6);
        b7 = (CheckBox)findViewById(R.id.checkBox7);
        b8 = (CheckBox)findViewById(R.id.checkBox8);
        b9 = (CheckBox)findViewById(R.id.checkBox9);
        b10 = (CheckBox)findViewById(R.id.checkBox10);
        b11 = (CheckBox)findViewById(R.id.checkBox11);
        b12 = (CheckBox)findViewById(R.id.checkBox12);
        enr = (Button)findViewById(R.id.enregistrer);
    }

    public void enregistrer(){

    }
}
