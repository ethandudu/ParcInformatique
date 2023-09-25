public class Telephone extends Materiel {
    private String OS;

    public Telephone(Integer SN, String Os){
        super(SN, "Telephone");
        OS = Os;
    }

    public void Affiche(){
        System.out.println("Type: Telephone SN: "+this.getSN()+" OS: "+OS);
    }
    
    public String export(){
        return getSN() + ";" + getType() + ";" + OS;
    }
}
