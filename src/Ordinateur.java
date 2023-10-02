public class Ordinateur extends Materiel {

    private String OS;
    private String CPU;
    private Integer RAM;
    private String GPU;

    public Ordinateur(Integer SerialNumber, String Os, String Cpu, Integer Ram, String Gpu){
        super(SerialNumber, "Ordinateur");
        OS = Os;
        CPU = Cpu;
        RAM = Ram;
        GPU = Gpu;
    }


    public void Affiche(){
        System.out.println("Type: Ordinateur SN: " + getSN() + " OS: " + OS + " CPU: " + CPU + " RAM: " + RAM + "Go GPU: " + GPU);
    }
    
    public String export(){
        return getType() + ";" + getSN() +  ";" + OS + ";" + CPU + ";" + RAM + ";" + GPU;
    }
}
