package collectifyamoukoudji.projetseg;

import java.util.ArrayList;

public class Organisation {

    private String _id;
    private String _organisationName;
    private String _organisationDescription;
    private Boolean _isLiscenced;
    private Address _organisationAddress;
    private ArrayList<Horraire> _organisationHorraire;
    private ArrayList<String> _services;




    public Organisation() {
        this._id = "";
        this._organisationName = "";
        this._organisationDescription = "";
        this._isLiscenced = false;
        this._organisationAddress = new Address();
        this._services = new ArrayList<String>();
        this._organisationHorraire = new ArrayList<Horraire>();
    }

    public Organisation(String id, String name, String description, boolean isLiscenced, Address address, ArrayList<String> list, ArrayList<Horraire> h) {
        this._id = id;
        this._organisationName = name;
        this._organisationDescription = description;
        this._isLiscenced = isLiscenced;
        this._organisationAddress = address;
        this._services = list;
        this._organisationHorraire = h;
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

    public ArrayList<String> get_services() {
        return _services;
    }

    public void set_services(ArrayList<String> _services) {
        this._services = _services;
    }





    public ArrayList<Horraire> get_organisationHorraire() {
        return _organisationHorraire;
    }

    public void set_organisationHorraire(ArrayList<Horraire> _organisationHorraire) {
        this._organisationHorraire = _organisationHorraire;
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
