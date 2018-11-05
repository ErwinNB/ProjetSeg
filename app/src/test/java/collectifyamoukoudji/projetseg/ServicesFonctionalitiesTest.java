package collectifyamoukoudji.projetseg;

//import android.support.test.annotation.UiThreadTest;
//import android.support.test.rule.ActivityTestRule;
import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class ServicesFonctionalitiesTest {

    @Rule
    //public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule<MainActivity>(ServiceActivity.class);
    private ServiceActivity service;
    //private Context context = service.getApplicationContext();

    @Test
    public void testAddService() throws Exception {

        EditText serviceName = service.findViewById(R.id.editTextService);
        serviceName.setText("Maintenance IT");
        EditText serviceRate = service.findViewById(R.id.editTextService);
        serviceRate.setText("200");

        
    }

}
