public class Materiel {

    private Integer SN;
    private String type;

    public Materiel(Integer SerialNumber, String Type){
        SN = SerialNumber;
        type = Type;
    }

    public Integer getSN(){
        return SN;
    }

    public void setSN(Integer SerialNumber){
        SN = SerialNumber;
    }

    public String getType(){
        return type;
    }

    public void setType(String Type){
        type = Type;
    }

    public void Affiche(){
        System.out.println("Materiel: " + SN);
    }
}
