package collectifyamoukoudji.projetseg;

public class Organisation {

    private String _id;
    private String _organisationName;
    private String _organisationDescription;
    private Boolean _isLiscenced;
    private Address _organisationAddress;



    public Organisation() {
        this._id = "";
        this._organisationName = "";
        this._organisationDescription = "";
        this._isLiscenced = false;
        this._organisationAddress = new Address();
    }

    public Organisation(String id, String name, String description, boolean isLiscenced) {
        this._id = id;
        this._organisationName = name;
        this._organisationDescription = description;
        this._isLiscenced = isLiscenced;
    }

    public Organisation(String id, String name, String description, boolean isLiscenced, Address address) {
        this._id = id;
        this._organisationName = name;
        this._organisationDescription = description;
        this._isLiscenced = isLiscenced;
        this._organisationAddress = address;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_organisationName() {
        return _organisationName;
    }

    public void set_organisationName(String _organisationName) {
        this._organisationName = _organisationName;
    }

    public String get_organisationDescription() {
        return _organisationDescription;
    }

    public void set_organisationDescription(String _organisationDescription) {
        this._organisationDescription = _organisationDescription;
    }

    public Boolean get_isLiscenced() {
        return _isLiscenced;
    }

    public void set_isLiscenced(Boolean _isLiscenced) {
        this._isLiscenced = _isLiscenced;
    }

    public Address get_organisationAddress() {
        return _organisationAddress;
    }

    public void set_organisationAddress(Address _organisationAddress) {
        this._organisationAddress = _organisationAddress;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "_id='" + _id + '\'' +
                ", _organisationName='" + _organisationName + '\'' +
                ", _organisationDescription='" + _organisationDescription + '\'' +
                ", _isLiscenced=" + _isLiscenced +
                ", _organisationAddress=" + _organisationAddress +
                '}';
    }
}
