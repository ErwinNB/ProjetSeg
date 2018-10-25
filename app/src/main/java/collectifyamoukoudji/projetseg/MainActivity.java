package collectifyamoukoudji.projetseg;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1 = (login)findViewById(R.id.button);
    button1.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            Intent intent = new Intent(LogIn.this, LogIn.class);
            startActivity(intent);
        }
    });
    Button button2 = (sign_up)findViewById(R.id.button);
    button2.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            Intent intent = new Intent(sign_up.this, sign_up.class);
            startActivity(intent);
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
