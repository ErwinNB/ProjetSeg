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
    private TextView reste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupUI();

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
    }

//    public void openLogin(){
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//    }

    public void getInfo(){
        Users client;
    }

}
