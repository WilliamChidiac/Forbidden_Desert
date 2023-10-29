package modele;


import java.util.ArrayList;
import java.util.HashMap;

public class Case {
    private static HashMap<Integer, Case> idCase = new HashMap<>();
    public static HashMap getHash(){return idCase;}
    public static Case getCaseById(int id){ return idCase.get(id); }
    private static int nextId = 0;
    private final int id;
    private CaseType type;
    private int nb_sable;
    private boolean equipment;
    private boolean explored;
    private ArrayList<Desert.PieceMachine> pieceMachine = new ArrayList<>();

    private int bouclierSolaire = 0;
    public Case(int ns, CaseType t){
        idCase.put(nextId, this);
        this.type = t;
        this.nb_sable = ns;
        this.explored = false;
        this.equipment = false;
        this.id = nextId;
        nextId++;
    }
    //getters

    public int getIdCase(){return this.id;}
    public int getNb_sable(){return this.nb_sable;}
    public CaseType getType(){return this.type;}
    public boolean get_explored(){return this.explored;}
    public boolean get_equipment(){return this.equipment;}
    //méthodes
    public void ActiveBouclierSolaire(){bouclierSolaire = 3;}
    public void has_equipment(){this.equipment = true;}
    public void takeEquipement(){this.equipment = false;}
    public void explore(){this.explored = true;}
    public void blaster(){this.nb_sable = 0;}
    public void creuse(){this.nb_sable--;}
    public void tempeteSouffle(){this.nb_sable++;}
    public void addPieceMachine(int pm){

        pieceMachine.add(Desert.PieceMachine.values()[pm]);
    }
    public boolean hasPiece(){
        return pieceMachine.size() != 0;
    }
    public int prendPiece(){
        return pieceMachine.remove(0).ordinal();
    }
    public int nombrePiece(){
        return this.pieceMachine.size();
    }


    public enum CaseType{
        Indice_ligne_Moteur("indice ligne moteur", 0),
        Indice_ligne_Gouvernail("indice ligne gouvernail", 0),
        Indice_ligne_Energie("indice ligne energie", 0),
        Indice_ligne_Helice("indice ligne Helice", 0),
        Indice_col_Moteur("indice colonne moteur", 1),
        Indice_col_Gouvernail("indice colonne gouvernail", 1),
        Indice_col_Energie("indice colonne energie", 1),
        Indice_col_Helice("indice colonne Helice", 1),
        Campement("campement"),
        OeilTemp("Oeil_Tempête"),
        Mirage("mirage"),
        Point_eau("Point_eau"),
        Tunnel("tunnel"),
        Piste_decollage("piste_decollage"),
        ;
        private final String nomcase;
        private final int iscolone;
        CaseType(String s) {
           this.nomcase = s;
           this.iscolone = -1;
        }
        CaseType(String s, int c) {
            this.nomcase =s;
            this.iscolone = c;
        }
        public String to_string(){return this.nomcase;}
        public boolean isColonne(){return this.iscolone == 1;}
    }
}
