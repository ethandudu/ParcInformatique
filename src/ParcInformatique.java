import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ParcInformatique{
    private String Name;
    private ArrayList <Materiel> MaterielList;

    public ParcInformatique(){
        this.MaterielList = new ArrayList<Materiel>();
    }

    public ParcInformatique(String Name){
        this.Name = Name;
        this.MaterielList = new ArrayList<Materiel>();

    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void Add(Materiel Materiel){
        this.MaterielList.add(Materiel);
    }

    public void Remove(Integer SN){
        for (int i = 0; i < this.MaterielList.size(); i++){
            if (this.MaterielList.get(i).getSN().equals(SN)) {
                this.MaterielList.remove(i);
                System.out.println("Matériel supprimé avec succès");
                break;
            }
        }
    }

    public void Affiche(){
        System.out.println("Affichage du parc informatique nommé " + Name + ":");
        for (int i = 0; i < this.MaterielList.size(); i++) {
            this.MaterielList.get(i).Affiche();
        }
    }

    public void Search(Integer SN){
        System.out.println("Recherche du SN "+SN+"...");
        for (int i = 0; i < this.MaterielList.size(); i++){
            if (this.MaterielList.get(i).getSN().equals(SN)) {
                System.out.println("Matériel trouvé : ");
                this.MaterielList.get(i).Affiche();
                break;
            }
        }
        //System.out.println("Matériel introuvable, merci de vérifier le SN");
    }

    public void Search(String type){
        System.out.println("Recherche du type "+type+"...");
        for (int i = 0; i < this.MaterielList.size(); i++){
            if (this.MaterielList.get(i).getType() == type) {
                System.out.println("Matériel trouvé : ");
                this.MaterielList.get(i).Affiche();
            }
        }
    }
    public void exportparc(){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream("parc.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.Name);
            for (int i = 0; i < this.MaterielList.size(); i++) {
                oos.writeObject(this.MaterielList.get(i).export());
            }
            oos.writeObject("EOF");
            oos.flush();
            oos.close();
            fos.close();
            System.out.println("Parc informatique exporté avec succès");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void importparc() throws IOException{
        FileInputStream fis = new FileInputStream("parc.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            String[] namepart = ((String) ois.readObject()).split(";");
            this.Name = namepart[0];
            while (true) {
                String line = (String) ois.readObject();
                if (line.equals("EOF")) {
                    break;
                }
                String[] parts = line.split(";");
                if (parts[1].equals("Ordinateur")) {
                    Materiel Ordi = new Ordinateur(Integer.parseInt(parts[0]), parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
                    this.MaterielList.add(Ordi);
                } else if (parts[1].equals("Switch")) {
                    Materiel Switch = new Switch(Integer.parseInt(parts[0]), Integer.parseInt(parts[2]));
                    this.MaterielList.add(Switch);
                } else if (parts[1].equals("Telephone")) {
                    Materiel Tel = new Telephone(Integer.parseInt(parts[0]), parts[2]);
                    this.MaterielList.add(Tel);
                }
            }
        } catch (Exception e) {
            
        }
        ois.close();
        fis.close();
    }

    public static void main(String[] args) throws Exception {
        ParcInformatique parc = new ParcInformatique();
        Scanner s;
        s = null;
        try {
            s = new Scanner(System.in);
            System.out.println("Bienvenue dans l'outil de gestion de parc informatique");
            System.out.println("Merci de taper votre choix parmi la sélection suivante :");
            System.out.println("1 - Modifier le nom du parc \n2 - Ajouter un matériel \n3 - Supprimer un matériel \n4 - Afficher le parc informatique \n5 - Rechercher un matériel \n6 - Exporter le parc informatique \n7 - Importer un parc informatique");

            while (s.hasNextLine()){
                
                int choix = s.nextInt();
                if (choix == 1){
                    System.out.println("Merci de saisir le nom du parc informatique :");
                    String name = s.next();
                    parc.setName(name);
                    System.out.println("Parc informatique créé avec succès");
                } else if (choix == 2) {
                    System.out.println("Merci de saisir le type du matériel à ajouter :");
                    System.out.println("1 - Ordinateur \n2 - Switch \n3 - Téléphone");
                    int type = s.nextInt();
                    if (type == 1){
                        System.out.println("SN :");
                        int SN = s.nextInt();
                        System.out.println("OS :");
                        String OS = s.next();
                        System.out.println("CPU :");
                        String CPU = s.next();
                        System.out.println("RAM :");
                        int RAM = s.nextInt();
                        System.out.println("GPU :");
                        String GPU = s.next();
                        Materiel Ordi = new Ordinateur(SN, OS, CPU, RAM, GPU);
                        parc.Add(Ordi);
                    } else if (type == 2){
                        System.out.println("SN :");
                        int SN = s.nextInt();
                        System.out.println("Nombre de ports :");
                        int ports = s.nextInt();
                        Materiel Switch = new Switch(SN, ports);
                        parc.Add(Switch);
                    } else if (type == 3){
                        System.out.println("SN :");
                        int SN = s.nextInt();
                        System.out.println("OS :");
                        String OS = s.next();
                        Materiel Tel = new Telephone(SN, OS);
                        parc.Add(Tel);
                    }
                } else if (choix == 3){
                    System.out.println("Entrez le SN du matériel à supprimer :");
                    int SN = s.nextInt();
                    parc.Remove(SN);              
                } else if (choix == 4){
                    parc.Affiche();
                } else if (choix == 5){
                    System.out.println("Rechercher par SN");
                    int SN = s.nextInt();
                    parc.Search(SN);
                } else if (choix == 6){
                    parc.exportparc();
                } else if (choix == 7){
                    parc.importparc();
                }
                System.out.println("Merci de taper votre choix parmi la sélection suivante :");
                System.out.println("1 - Modifier le nom du parc \n2 - Ajouter un matériel \n3 - Supprimer un matériel \n4 - Afficher le parc informatique \n5 - Rechercher un matériel \n6 - Exporter le parc informatique \n7 - Importer un parc informatique");
                
            }
        } finally {
            s.close();
        }
    }

}
