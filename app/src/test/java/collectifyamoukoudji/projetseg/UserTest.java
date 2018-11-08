package collectifyamoukoudji.projetseg;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    Users user = new Users("Lui", "LeTest", "email@google.com","Fournisseur");

    @Test
    public void checkUserFirstAndLastName(){
        assertEquals("check the users first name", "Lui", user.get_firstname());
        System.out.println("First name --> OK");
        assertEquals("check the users last name", "LeTest", user.get_lastname());
        System.out.println("Last name --> OK");
    }

    @Test
    public void checkUserEmail() {
        assertEquals("check the users email", "email@google.com", user.get_email());
        System.out.println("Email --> OK");
    }

    @Test
    public void checkUserType() {
        assertEquals("check the users type", "Fournisseur", user.get_type());
        System.out.println("Type --> OK");
    }

}
