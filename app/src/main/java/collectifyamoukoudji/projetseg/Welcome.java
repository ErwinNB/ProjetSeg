package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class Welcome extends AppCompatActivity {

    private ProSwipeButton proSwipeButton;
    private TextView welcome;

    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupUI();
        getInfo();
        proSwipeButton.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                proSwipeButton.showResultIcon(true);
//                openLogin();
            }
        });
    }

    public void setupUI(){


        proSwipeButton = (ProSwipeButton) findViewById(R.id.btn_awesome);
        welcome = (TextView)findViewById(R.id.Welcome);
        info =  (TextView)findViewById(R.id.textInfo);
    }

//    public void openLogin(){
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//    }

    public void getInfo(){
        String email = getIntent().getStringExtra("UserEmail");
        String type = getIntent().getStringExtra("UserType");
        info.setText(email + " Vous etes authentifié en tant que "+type);
    }

}
