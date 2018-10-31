package collectifyamoukoudji.projetseg;

public class Service {
    private String _id;
    private String _servicename;
    private double _rate;

    public Service() {
    }
    public Service(String id, String servicename, double rate) {
        _id = id;
        _servicename = servicename;
        _rate = rate;
    }
    public Service(String servicename, double rate) {
        _servicename = servicename;
        _rate = rate;
    }

    public void setId(String id) {
        _id = id;
    }
    public String getId() {
        return _id;
    }
    public void setServiceName(String servicename) {
        _servicename = servicename;
    }
    public String getServiceName() {
        return _servicename;
    }
    public void setRate(double rate) {
        _rate = rate;
    }
    public double getRate() {
        return _rate;
    }
}