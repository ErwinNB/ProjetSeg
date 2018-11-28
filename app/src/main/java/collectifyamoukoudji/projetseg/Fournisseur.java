package collectifyamoukoudji.projetseg;

public class Fournisseur extends Users{

    /**
     * Contains a Organisation type of value
     * that is assigned for the adress of the user.
     */
    private  Organisation _currentOrganisation;

    public Fournisseur(String id, String firstname, String lastname, String email, String type){
        super(id, firstname, lastname, email, type);
        _currentOrganisation = new Organisation();
    }

    public Fournisseur(String id, String firstname, String lastname, String email, String type, Organisation organisation){
        super(id, firstname, lastname, email, type);
        _currentOrganisation = organisation;
    }

    @Override
    public Organisation get_currentOrganisation() {
        return _currentOrganisation;
    }

    @Override
    public void set_currentOrganisation(Organisation _currentOrganisation) {
        this._currentOrganisation = _currentOrganisation;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "_currentOrganisation=" + _currentOrganisation +
                '}';
    }
}
