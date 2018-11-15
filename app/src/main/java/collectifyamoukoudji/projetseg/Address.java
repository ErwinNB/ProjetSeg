package collectifyamoukoudji.projetseg;

public class Address {
    //Instance variables ************************************************

    /**
     * Contains a String type of value
     * that is assigned for the id of the Address.
     */
    private  String _id;
    /**
     * Contains a String type of value
     * that is assigned for the street number of the Address.
     */
    private  String _num;
    /**
     * Contains a String type of value
     * that is assigned for the street name of the Address.
     */
    private  String _sname;
    /**
     * Contains a String type of value
     * that is assigned for the postal code of the Address.
     */
    private  String _pcode;
    /**
     * Contains a String type of value
     * that is assigned for the city of the Address.
     */
    private  String _city;
    /**
     * Contains a String type of value
     * that is assigned for the country of the Address.
     */
    private  String _country;
    /**
     * Constructor
     */
    public Address(){}
    /**
     * Constructor
     */
    public Address(String id, String num, String streetname, String postalcode, String city, String country){

        this._id = id;
        this._num = num;
        this._sname = streetname;
        this._pcode = postalcode;
        this._city = city;
        this._country = country;

    }

    //Instance methods **************************************************
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_num() {
        return _num;
    }

    public void set_num(String _num) {
        this._num = _num;
    }

    public String get_sname() {
        return _sname;
    }

    public void set_sname(String _sname) {
        this._sname = _sname;
    }

    public String get_pcode() {
        return _pcode;
    }

    public void set_pcode(String _pcode) {
        this._pcode = _pcode;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }


    @Override
    public String toString() {
        return "Address{" +
                "_id='" + _id + '\'' +
                ", _num='" + _num + '\'' +
                ", _sname='" + _sname + '\'' +
                ", _pcode='" + _pcode + '\'' +
                ", _city='" + _city + '\'' +
                ", _country='" + _country + '\'' +
                '}';
    }
}
