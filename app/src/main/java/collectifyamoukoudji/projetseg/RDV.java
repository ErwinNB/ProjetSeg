package collectifyamoukoudji.projetseg;

public class RDV {

    private String _id;
    private String _clientID;
    private String _fournissuerID;
    private String _organisationName;
    private String _clientemail;
    private String _clientphoneNumber;
    private String _timeAndDate;
    private boolean _clientFlag;
    private boolean _fourFlag;

    public RDV(String _id, String _clientID, String _fournissuerID, String _organisationName, String _clientemail, String _clientphoneNumber, String _timeAndDate, boolean _clientFlag, boolean _fourFlag) {

        this._id = _id;
        this._clientID = _clientID;
        this._fournissuerID = _fournissuerID;
        this._organisationName = _organisationName;
        this._clientemail = _clientemail;
        this._clientphoneNumber = _clientphoneNumber;
        this._timeAndDate = _timeAndDate;
        this._clientFlag = _clientFlag;
        this._fourFlag = _fourFlag;
    }

    @Override
    public String toString() {
        return "RDV{" +
                "_id='" + _id + '\'' +
                ", _clientID='" + _clientID + '\'' +
                ", _fournissuerID='" + _fournissuerID + '\'' +
                ", _organisationName='" + _organisationName + '\'' +
                ", _clientemail='" + _clientemail + '\'' +
                ", _clientphoneNumber='" + _clientphoneNumber + '\'' +
                ", _timeAndDate='" + _timeAndDate + '\'' +
                ", _clientFlag=" + _clientFlag +
                ", _fourFlag=" + _fourFlag +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_clientID() {
        return _clientID;
    }

    public void set_clientID(String _clientID) {
        this._clientID = _clientID;
    }

    public String get_fournissuerID() {
        return _fournissuerID;
    }

    public void set_fournissuerID(String _fournissuerID) {
        this._fournissuerID = _fournissuerID;
    }

    public String get_organisationName() {
        return _organisationName;
    }

    public void set_organisationName(String _organisationName) {
        this._organisationName = _organisationName;
    }

    public String get_clientemail() {
        return _clientemail;
    }

    public void set_clientemail(String _clientemail) {
        this._clientemail = _clientemail;
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

    public boolean is_clientFlag() {
        return _clientFlag;
    }

    public void set_clientFlag(boolean _clientFlag) {
        this._clientFlag = _clientFlag;
    }

    public boolean is_fourFlag() {
        return _fourFlag;
    }

    public void set_fourFlag(boolean _fourFlag) {
        this._fourFlag = _fourFlag;
    }


}
