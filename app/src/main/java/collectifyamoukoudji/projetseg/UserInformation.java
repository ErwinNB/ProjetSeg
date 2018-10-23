package collectifyamoukoudji.projetseg;

public class UserInformation {

    private String name;
    private String email;
    private String type;

    public UserInformation(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String t) {
        this.type = t;
    }
}
