package collectifyamoukoudji.projetseg;

import java.util.ArrayList;

public class Reservation {
    private String _id;
    private String _organisationName;
    private String _fournisseurName;
    private String _clientname;
    private long date;
    private ArrayList<String> selected_time;
    private String type_service;

   public Reservation( String _id, String _organisationName, String _fournisseurName, String _clientname,
                       Long d, ArrayList<String> selected_time, String type_service){
       this._id = _id;
       this._organisationName = _organisationName;
       this._clientname = _clientname;
       date = d;
       this.type_service = type_service;
       this._fournisseurName = _fournisseurName;
       this.selected_time = selected_time;
   }

    public String get_clientname() {
        return _clientname;
    }

    public void set_clientname(String _clientname) {
        this._clientname = _clientname;
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

    public long getdate() {
        return date;
    }

    public void setdate(Long date) {
        this.date = date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public ArrayList<String> getSelected_time() {
        return selected_time;
    }

    public void setSelected_time(ArrayList<String> selected_time) {
        this.selected_time = selected_time;
    }

     public String getType_service() {
        return type_service;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

}
