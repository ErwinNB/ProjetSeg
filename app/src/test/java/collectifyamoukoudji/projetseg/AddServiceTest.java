package collectifyamoukoudji.projetseg;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

public class AddServiceTest {

    @Rule
    public ActivityTestRule<ServiceActivity> sActivityTestRule= new ActivityTestRule<ServiceActivity>(ServiceActivity.class);
    private ServiceActivity serviceActivity=null;
    private TextView text;
    private TextView rate;

    @Before
    public void setUp() throws Exception {
        serviceActivity = sActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void check(){
        text = serviceActivity.findViewById(R.id.editTextService);
        rate = serviceActivity.findViewById(R.id.editTextRate);
        text.setText("Electricite");
        rate.setText("200");
        String name = text.getText().toString();
        assertNotEquals("Electricite", name);
    }

}
