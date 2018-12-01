package collectifyamoukoudji.projetseg;

public class Reservation {
    private Horraire horaire;
    private String type_service;

   public Reservation( Horraire h, String s_t){
       horaire = h;
       type_service = s_t;
   }

    public Horraire getHoraire() {
        return horaire;
    }

    public String getType_service() {
        return type_service;
    }

    public void setHoraire(Horraire horaire) {
        this.horaire = horaire;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }
}
