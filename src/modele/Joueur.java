package modele;

import java.util.ArrayList;

/**
 * class joueur ne pas confondre ce qu'un Joueur peut fair de ceque l'aventurier peut fair
 */
public class Joueur {
    private String nom;
    private Aventurier aventurier;
    private int niv_eau;
    private int action;
    private int IdpositionCase;
    private int IdpositionCasePrecedente;
    private ArrayList<CarteEquipement.TypeEquipement> inventaire;

    /**
     * creer le joueur a paritr de son nom et de la carte aventurier qui lui a ete attribue
     * @param nom nom du joueur
     * @param aventurier son personnage
     */
    public Joueur(String nom, Aventurier aventurier){
        this.nom = nom;
        this.aventurier = aventurier;
        this.niv_eau = aventurier.getMax_eau();
        this.action = 100;
        this.inventaire = new ArrayList<>();


    }
    /**
     * invetaire
     */
    public ArrayList<CarteEquipement.TypeEquipement> getInvetaire(){return this.inventaire;}
    public int getNombreInventaire(){return this.inventaire.size();}

    /**
     *
     * @return le nom du joueur
     */
    public String getNom(){
        return this.nom;
    }
    public void setNom(String n){this.nom = n;}


    /**
     *
     * @return le personnage du joueur
     */
    public Aventurier getAventurier(){
        return this.aventurier;
    }

    /**
     *
     * @return le nombre d'action restante avant la faim de son tour
     */
    public int getAction(){
        return this.action;
    }
    public void depenseAction(){this.action--;}

    /**
     * @return la position du Joueur dans la case
     */
    public int getIdPositionCase(){
        return this.IdpositionCase;
    }
    public int getIdPositionCasePrecedente(){
        return this.IdpositionCasePrecedente;
    }
    /**
     * A chaque tour un joueur peut effectuer 4 actions
     */
    public void FinTour(){
        action = 4;
    }

    public int getNiv_eau(){return this.niv_eau;}
    /**
     * lorsqu'il sont sur une case puis il ramasse de l'eau si il n'a pas atteint la capacite maximal du personnage
     */
    public void ramasseEau() {
        niv_eau++;
        if (aventurier.getNomAventurier().equals("Porteuse d'eau")){
            niv_eau++;
        }
        if(niv_eau > aventurier.getMax_eau())
            niv_eau = aventurier.getMax_eau();
    }

    /**
     * lorsqu'un autre joueur lui donne de l'eau, on incremente le niv d'eau de this
     */
    public boolean recoitEau() {
        if (niv_eau < aventurier.getMax_eau()){
            niv_eau++;
            return true;
        }
        return false;
    }

    /**
     * permet de donner de l'eau a un autre joueur
     * @param j le joueur qui recevra l'eau
     */
    public void donneEau(Joueur j){
        if(j.recoitEau())
            niv_eau--;
    }

    /**
     * boit de l'eau quand vague de chaleur
     */
    public void boitEau(){niv_eau--;}

    /**
     * place le joueur sur la case donne
     * @param id l'index de la case
     */
    public void setPositionCase(int id){
        this.IdpositionCase = id;
    }

    /**
     * defini la position de la case a laquel le joueur etait avant de ce deplacer
     * @param id l'index de cette case
     */
    public void setIPositionCasePrecedente(int id){
        this.IdpositionCasePrecedente = id;
    }

    /**
     * deplace le joueur a la case voulu.
     * ont update donc la position du joueur courrante et initial
     * @param newCase la case a laquel il se deplace
     */
    public void deplaceJoueur(int newCase){
        if(action > 0){
            this.IdpositionCasePrecedente = this.IdpositionCase;
            this.IdpositionCase = newCase;
            this.action--;
        }
    }

    public void creuse(int id){
        if (action > 0){
            Case c = Case.getCaseById(id);
            c.creuse();
            action--;
        }
    }
    /**public void utiliseEquipement(CarteEquipement.TypeEquipement gear){
        this.inventaire.remove(gear);
    }
    public void utiliseEquipement(int index){
        this.inventaire.remove(index);
    }**/
    public void ramasseEquipement(CarteEquipement.TypeEquipement gear){
        this.inventaire.add(gear);
        Case c = Case.getCaseById(this.IdpositionCase);
        c.takeEquipement();
    }
    public void utiliseBlaster(int idcase){
        Case c = Case.getCaseById(idcase);
        c.blaster();
    }
    public void utiliseBouclierSolaire(){
        Case c = Case.getCaseById(IdpositionCase);

    }
    public void utiliseJetpack(int id){
        this.deplaceJoueur(id);
    }
    public void utiliseEquipement(CarteEquipement.TypeEquipement c){
        this.inventaire.remove(c);
        if (CarteEquipement.TypeEquipement.BOUCLIER_SOLAIRE == c){
            utiliseBouclierSolaire();
        }

    }
}