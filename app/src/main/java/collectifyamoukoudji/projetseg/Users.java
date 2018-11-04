package collectifyamoukoudji.projetseg;

public class Users {

    private  String _id;
    private  String _firstname;
    private  String _lastname;
    private  String _type;
    private  String _email;

    public Users(){}

    public Users (String id, String firstname, String lastname, String email, String type){
        this._email = email;
        this._firstname =firstname;
        this._id = id;
        this._lastname = lastname;
        this._type = type;
    }

    public Users ( String firstname, String lastname, String email, String type){
        this._email = email;
        this._firstname = firstname;
        this._lastname= lastname;
        this._type = type;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void set_firstname(String firstname) {
        _firstname = firstname;
    }
    public String get_firstname() {
        return _firstname;
    }
    public void set_lastname(String lastname) {
        _lastname = lastname;
    }
    public String get_lastname() {
        return _lastname;
    }
    public void set_type(String type) {
        _type = type;
    }
    public String get_type() {
        return _type;
    }
    public void set_email(String email) {
        _email = email;
    }
    public String get_email() {
        return _email;
    }
    public String toString(){
        return _email;
    }

}

