package collectifyamoukoudji.projetseg;

public class Reservation {
    private Long date;
    private String type_service;

   public Reservation( Long d, String s_t){
       date = d;
       type_service = s_t;
   }

    public Long getdate() {
        return date;
    }

    public String getType_service() {
        return type_service;
    }

    public void setdate(Long date) {
        this.date = date;
    }

    public void setType_service(String type_service) {
        this.type_service = type_service;
    }
}
