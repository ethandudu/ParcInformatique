public class Switch extends Materiel {
    private Integer PortsEthernet;

    public Switch(Integer SN, Integer NbPortsEthernet){
        super(SN, "Switch");
        PortsEthernet = NbPortsEthernet;
    }

    public void Affiche(){
        System.out.println("Type: Switch SN: "+this.getSN()+" Nb Ports Ethernet: "+PortsEthernet);
    }
    
}
