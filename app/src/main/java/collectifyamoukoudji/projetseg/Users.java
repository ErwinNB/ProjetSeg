package collectifyamoukoudji.projetseg;

public class Users {

    //Instance variables ************************************************

    /**
     * Contains a String type of value
     * that is assigned for the id of the user.
     */
    private  String _id;
    /**
     * Contains a String type of value
     * that is assigned for the fistname of the user.
     */
    private  String _firstname;
    /**
     * Contains a String type of value
     * that is assigned for the lastname of the user.
     */
    private  String _lastname;
    /**
     * Contains a String type of value
     * that is assigned for the type of the user.
     */
    private  String _type;
    /**
     * Contains a String type of value
     * that is assigned for the email of the user.
     */
    private  String _email;
    /**
     * Contains a Adress type of value
     * that is assigned for the adress of the user.
     */
    private  Address _currentAddress;

    /**
     * Default constructor
     */
    public Users(){}
    /**
     * Constructor
     */
    public Users (String id, String firstname, String lastname, String email, String type){
        this._email = email;
        this._firstname =firstname;
        this._id = id;
        this._lastname = lastname;
        this._type = type;
    }
    /**
     * Constructor
     */
    public Users ( String firstname, String lastname, String email, String type){
        this._email = email;
        this._firstname = firstname;
        this._lastname= lastname;
        this._type = type;
    }

    /**
     * Constructor
     */
    public Users (String id, String firstname, String lastname, String email, String type, Address address){
        this._email = email;
        this._firstname =firstname;
        this._id = id;
        this._lastname = lastname;
        this._type = type;
        this._currentAddress = address;
    }


    //Instance methods **************************************************
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
    public Address get_currentAddress() {
        return _currentAddress;
    }
    public void set_currentAddress(Address _currentAddress) {
        this._currentAddress = _currentAddress;
    }
}

