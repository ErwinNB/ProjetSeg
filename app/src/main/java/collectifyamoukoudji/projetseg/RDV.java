package collectifyamoukoudji.projetseg;

public class RDV {

    private String _id;
    private String _organisationName;
    private String _clientname;
    private String _clientphoneNumber;
    private String _timeAndDate;

    public RDV(String _id, String _organisationName, String _clientname, String _clientphoneNumber, String _timeAndDate) {

        this._id = _id;
        this._organisationName = _organisationName;
        this._clientname = _clientname;
        this._clientphoneNumber = _clientphoneNumber;
        this._timeAndDate = _timeAndDate;
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

    public String get_clientname() {
        return _clientname;
    }

    public void set_clientname(String _clientname) {
        this._clientname = _clientname;
    }

    public String get_clientphoneNumber() {
        return _clientphoneNumber;
    }

    public void set_clientphoneNumber(String _clientphoneNumber) {
        this._clientphoneNumber = _clientphoneNumber;
    }

    public String get_timeAndDate() {
        return _timeAndDate;
    }

    public void set_timeAndDate(String _timeAndDate) {
        this._timeAndDate = _timeAndDate;
    }


}
