package collectifyamoukoudji.projetseg;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceTest {

    Service service = new Service("Toilette", 200);

    @Test
    public void checkSerciceName(){
        assertEquals("check the name of the service", "Toilette", service.getServiceName());
        System.out.println("Service name --> OK");
    }

    @Test
    public void checkServiceRate(){
        assertEquals("check the rate of the service", "200.0", String.valueOf(service.getRate()));
        System.out.println("Service rate --> OK");
    }

}
