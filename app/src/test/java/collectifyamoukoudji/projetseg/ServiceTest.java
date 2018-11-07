package collectifyamoukoudji.projetseg;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceTest {

    @Test
    public void checkSerciceName(){
        Service service = new Service("Toilette", 200);
        assertEquals("check the name of the service", "Toilette", service.getServiceName());
    }

    @Test
    public void checkServiceRate(){
        Service service = new Service("Toilette", 200.0);
        assertEquals("check the rate of the service", "200.0", String.valueOf(service.getRate()));
    }

}
