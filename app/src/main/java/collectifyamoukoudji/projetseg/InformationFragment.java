package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InformationFragment extends Fragment{

    View myView;
    private EditText numStreet;
    private EditText streetName;
    private EditText codePostal;
    private EditText ville;
    private EditText pays;
    private Button enregistrer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.information_layout, container, false);

        setupUI();

        return myView;
    }

    public void setupUI(){

        numStreet = (EditText) myView.findViewById(R.id.editTextNumber);
        streetName = (EditText) myView.findViewById(R.id.editTextStreetName);
        codePostal = (EditText) myView.findViewById(R.id.editTextCP);
        ville = (EditText) myView.findViewById(R.id.editTextCity);
        pays = (EditText) myView.findViewById(R.id.editTextCountry);
        enregistrer = (Button) myView.findViewById(R.id.btnEnregistrer);
    }

    public View.OnClickListener enregisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
