package collectifyamoukoudji.projetseg;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReservationTest {

    long date = System.currentTimeMillis();
    Reservation rdv = new Reservation("f3" ,"jiji", "fou", "cl", date, "IT", 2);

    @Test
    public void check_ID(){
        assertEquals("check the reservation id", "f3", rdv.get_id());
        System.out.println("ID --> OK");
    }

    @Test
    public void check_OrgName(){
        assertEquals("check the reservation organistion name", "jiji", rdv.get_organisationName());
        System.out.println("Organisation name --> OK");
    }

    @Test
    public void check_fourName(){
        assertEquals("check the reservation fournisseur name", "fou", rdv.get_fournisseurName());
        System.out.println("Fournisseur name --> OK");
    }

    @Test
    public void check_typeService(){
        assertEquals("check the reservation service type", "IT", rdv.getType_service());
        System.out.println("Service type --> OK");
    }
    
}
