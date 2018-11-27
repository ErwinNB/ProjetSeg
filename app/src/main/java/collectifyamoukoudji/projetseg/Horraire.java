package collectifyamoukoudji.projetseg;

import java.util.ArrayList;

public class Horraire {

    private ArrayList<ArrayList<Boolean>> _array;
    private String _id;
    private String _startweek;
    private boolean _flag;

    public Horraire(){
        _id = "";
        _startweek = "";
        _array = new ArrayList<>(7);
        for (int i = 0; i < 10 ; i++) {
            ArrayList<Boolean> tmp = new ArrayList<>(10);
            for (int j = 0; j < 7; j++) {
                tmp.add(false);
            }
            _array.add(tmp);
        }
        _flag = false;
    }

    public Horraire(String id, String startweek, boolean flag, ArrayList<ArrayList<Boolean>> _array) {
        this._array = _array;
        this._startweek = startweek;
        this._id = id;
        this._flag = flag;
    }

    public ArrayList<ArrayList<Boolean>> get_array() {
        return _array;
    }

    public void set_array(ArrayList<ArrayList<Boolean>> _array) {
        this._array = _array;
    }

    public boolean is_flag() {
        return _flag;
    }

    public void set_flag(boolean _flag) {
        this._flag = _flag;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_startweek() {
        return _startweek;
    }

    public void set_startweek(String _startweek) {
        this._startweek = _startweek;
    }
}
