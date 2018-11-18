package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

public class ContactFragment extends Fragment{

    View myView;
    private CheckBox[][] checkBoxes;
    private boolean[][] plageHorraire ;
    private Button  BtnEnregistrerPlage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.contact_layout, container, false);
        checkBoxes = new CheckBox[10][7];
        setupUI();
        return myView;
    }
    private void setupUI() {
        BtnEnregistrerPlage = (Button) myView.findViewById(R.id.buttonEnregistrerPlage);
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 7; j++) {
                String buttonID = "checkBox" +(7 * i + j+1);
                int resID = getResources().getIdentifier(buttonID, "id",getContext().getPackageName());
                checkBoxes[i][j] = (CheckBox) myView.findViewById(resID);
            }
        }
    }
}