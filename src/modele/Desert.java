package modele;

import Observer.Observable;
import controlleur.ControlleurJeu;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Desert extends Observable {
    private ArrayList<Joueur> listJoueur;
    private ArrayList<Case> plateau;
    private int niveau_tempete;
    private int indexJoueur;
    private Joueur joueurJouant;
    public int nombreCarteTempete;
    private boolean freezeForEquipement = false;
    private int indexCaseModifier;
    private int indexTempete;
    private boolean bougeTempete = false;
    private CarteTempete carteTempete;
    private CarteEquipement carteEquipement;
    private ArrayList<Integer> pieceTrouvable = new ArrayList<>();
    private ArrayList<Boolean> pieceTrouve = new ArrayList<>();
    private ArrayList<Joueur> receveurPossible = new ArrayList<>();
    private boolean blaster = false;
    private boolean jetpack = false;
    private boolean piocheEquipement = false;
    private CarteEquipement.TypeEquipement pioche;

    public enum PieceMachine{
        MOTEUR("Moteur"),
        GOUVERNAIL("Gouvernail"),
        ENERGIE("Energie"),
        HELICE("Helice");

        private String piece;
        PieceMachine(String n){
            this.piece = n;
        }
        public String getPiece(){return this.piece;}

    }




    public Desert(ArrayList<Joueur> listJoueur){
        ControlleurJeu.setDesert(this);
        this.indexJoueur = 0;
        this.listJoueur = listJoueur;
        this.niveau_tempete = 2;
        this.joueurJouant = listJoueur.get(indexJoueur);
        nombreCarteTempete = 2;
        this.carteTempete = new CarteTempete();
        this.carteEquipement = new CarteEquipement();
        for(int i = 0;i<4;i++){
            this.pieceTrouvable.add(-1);
            this.pieceTrouve.add(false);
        }

        this.indexTempete = Coordonne.from2Dto1D(2, 2);
        this.indexCaseModifier = indexTempete;
        ArrayList<Case> cases = new ArrayList<>();

        cases.add(new Case(0, Case.CaseType.Piste_decollage));

        cases.add(new Case(0, Case.CaseType.Indice_col_Energie));
        cases.add(new Case(0, Case.CaseType.Indice_col_Gouvernail));
        cases.add(new Case(0, Case.CaseType.Indice_col_Helice));
        cases.add(new Case(0, Case.CaseType.Indice_col_Moteur));
        cases.add(new Case(0, Case.CaseType.Indice_ligne_Gouvernail));
        cases.add(new Case(0, Case.CaseType.Indice_ligne_Energie));
        cases.add(new Case(0, Case.CaseType.Indice_ligne_Helice));
        cases.add(new Case(0, Case.CaseType.Indice_ligne_Moteur));

        cases.add(new Case(0, Case.CaseType.Mirage));
        cases.add(new Case(0, Case.CaseType.Point_eau));
        cases.add(new Case(0, Case.CaseType.Point_eau));

        cases.add(new Case(0, Case.CaseType.Tunnel));
        cases.add(new Case(0, Case.CaseType.Tunnel));
        cases.add(new Case(0, Case.CaseType.Tunnel));

        for (int i = 0; i<9; i++) {
            cases.add(new Case(0, Case.CaseType.Campement));
        }
        Collections.shuffle(cases);
        for(int i = 0; i <9; i++){
            cases.get(i).has_equipment();
        }
        Collections.shuffle(cases);
        cases.add(new Case(0, Case.CaseType.OeilTemp));
        this.plateau = cases;
        swapCases(24, new Coordonne(2, 2));
        cases.get(22).tempeteSouffle();
        cases.get(18).tempeteSouffle();
        cases.get(16).tempeteSouffle();
        cases.get(14).tempeteSouffle();
        cases.get(10).tempeteSouffle();
        cases.get(8).tempeteSouffle();
        cases.get(6).tempeteSouffle();
        cases.get(2).tempeteSouffle();


        int crashId = new Random().nextInt(23);
        for(Joueur j : listJoueur){
            j.setPositionCase(crashId);
            j.setIPositionCasePrecedente(crashId);
        }
    }
    public boolean isJetpack(){return jetpack;}
    public void resetJetpack(){jetpack = false;}
    public boolean isPiocheEquipement(){ return piocheEquipement;}
    public int getPiocheEquipement(){return pioche.ordinal();}
    public boolean isFreezeForEquipement(){return this.freezeForEquipement;}
    public int getNombreCarteTempete(){return this.nombreCarteTempete;}
    public ArrayList<Joueur> getListJoueur(){return this.listJoueur;}
    public boolean isBougeTempete(){return bougeTempete;}
    public void tempeteBougee(){this.bougeTempete = false;}
    public ArrayList<Integer> getPieceTrouvable(){return this.pieceTrouvable;}
    public int getIndexTempete(){return this.indexTempete;}
    public int getIndexCaseModifier(){return this.indexCaseModifier;}
    public ArrayList<Case> getPlateau(){return plateau;}
    public int getNiveau_tempete(){return this.niveau_tempete;}
    public int getTotal_sable(){
        int total = 0;
        for(Case c: plateau){
            total += c.getNb_sable();
        }
        return total;
    }
    public CarteTempete getCarteTempete(){return carteTempete;}

    public int getIndexById(int id){
        for(int index = 0; index < 25; index++){
            if (plateau.get(index).getIdCase() == id)
                return index;
        }
        throw new IndexOutOfBoundsException("l'id donne en parametre est inexsistante : " + id);
    }

    public void swapCases(int index1, int index2){
        Case c1 = this.plateau.get(index1);
        Case c2 = this.plateau.get(index2);
        this.plateau.set(index1, c2);
        this.plateau.set(index2, c1);
    }
    public void swapCases(int index1, Coordonne index2){
        int i2 = index2.from2Dto1D();
        swapCases(index1, i2);
    }
    public void deplaceTempeteDirection(Coordonne direction) {
        try {
            int newIndex = direction.ajouteVecteur1D(this.indexTempete);
            bougeTempete = true;
            Case c = plateau.get(newIndex);
            c.tempeteSouffle();
            swapCases(newIndex, indexTempete);
            indexCaseModifier = indexTempete;
            indexTempete = newIndex;
            this.notifyObservers();
        }catch (IndexOutOfBoundsException e){}

    }
    public void deplaceTempete(Coordonne direction, int distance){

        for(int i = 0; i < distance; i++){
            this.deplaceTempeteDirection(direction);
        }
        indexCaseModifier = indexTempete;
    }
    /**
     * calcul la liste deplacement possible (list des index pas des ids), il sont generalement horizontaux et veticaux,
     * mais peuvent etre diagonaux dependement de quelque joueur
     * @return ArrayList d'entier representer les indices des case qu'il peuvent atteindre
     */
    public ArrayList<Integer> getCaseAdjacente(){
        int idCurrentcase = joueurJouant.getIdPositionCase();
        Case currentCase = Case.getCaseById(idCurrentcase);
        int currentIndex = plateau.indexOf(currentCase);
        ArrayList<Integer> depPossible = new ArrayList<Integer>();
        ArrayList<Coordonne> vecteurDeplacement = this.joueurJouant.getAventurier().getDeplacement();
        for (Coordonne v : vecteurDeplacement){
            try{
                int newIndex = v.ajouteVecteur1D(currentIndex);
                depPossible.add(plateau.get(newIndex).getIdCase());
            }catch (ArrayIndexOutOfBoundsException e){}
        }
        return depPossible;
    }
    public Joueur getJoueurJouant() {
        return joueurJouant;
    }
    public ArrayList<Color> getJoueurColor(){
        ArrayList<Color> couleur = new ArrayList<>();
        for(Joueur J : listJoueur){
            couleur.add(J.getAventurier().getCouleur());
        }
        return couleur;
    }
    public int getIndexJoueur(){
        return this.indexJoueur;
    }


    public ArrayList<Joueur> getReceveurPossible(){
        return this.receveurPossible;
    }

    public boolean isPerdu(){
        int nombreSable = this.getTotal_sable();
        int niv_temp = niveau_tempete;
        for(Joueur j : listJoueur){
            if(j.getNiv_eau() == 0){
                return true;
            }
        }
        return nombreSable > 25 || niv_temp >7;
    }
    public boolean isGagne(){
        for(boolean b : pieceTrouve)
            if(!b) return false;
        for(Joueur j : listJoueur)
            if(j.getIdPositionCase() != 0) return false;
        return true;
    }
    public ArrayList<Integer> getTunnelExplore(){
        ArrayList<Integer> idTunnel = new ArrayList<>();
        for(Case c : plateau){
            if(c.get_explored() && c.getType() == Case.CaseType.Tunnel){
                idTunnel.add(c.getIdCase());
            }
        }
        return idTunnel;
    }

    /**
     * fonction qui deplace le joueur a la case id si il peut y acceder
     * il faudra modifier vuedesert et vueJoueur par ce que il a une action en moin si il y a changement
     * @param id l'id de la case ou on veut se deplacer
     *
     *
     *           !! reste a fair le case ou le joueuer se trouve dans un tunnel
     */
    public void clicGaucheCase(int id){

        ArrayList<Integer> depPossible = this.getCaseAdjacente();
        Case pos = Case.getCaseById(joueurJouant.getIdPositionCase());
        if (pos.get_explored() && pos.getType() == Case.CaseType.Tunnel)
            depPossible.addAll(this.getTunnelExplore());
        if (jetpack){
            for(int i = 0; i<24; i++){
                depPossible.add(i);
            }
        }
        int indexInDep = depPossible.indexOf(id);
        if ( indexInDep <= 0 || freezeForEquipement)
            return;// la case n'est pas accessible
        Case newCase = Case.getCaseById(id);
        Case currentCase = Case.getCaseById(joueurJouant.getIdPositionCase());
        if (newCase.getNb_sable()>1 || currentCase.getNb_sable() > 1)
            return;
        this.joueurJouant.deplaceJoueur(id);
        this.notifyObservers();
    }

    /**
     * fonction qui va permettre a un joueur de creuse si il y a
     * @param id id de la case ou on veut creuser
     */
    public void clicDroitCase(int id){
        ArrayList<Integer> depPossible = this.getCaseAdjacente();
        int indexInDep = depPossible.indexOf(id);
        if (indexInDep == -1 || freezeForEquipement)
            return;
        Case CaseDesable = Case.getCaseById(id);
        if (CaseDesable.getNb_sable() == 0)
            return;
        if (blaster){
            blaster = false;
            CaseDesable.blaster();
        }
        joueurJouant.creuse(id);
        this.indexCaseModifier = id;
        this.notifyObservers();
    }

    public void clicTempete(){
        if (joueurJouant.getAction() != 0 || freezeForEquipement || this.nombreCarteTempete == 0)
            return;
        carteTempete.genereCarteTempete();
        this.nombreCarteTempete--;
        switch (carteTempete.getCarte()){
            case VAGUE_DE_CHALEUR:
                for(Joueur j : listJoueur){
                    if(Case.getCaseById(j.getIdPositionCase()).getType() != Case.CaseType.Tunnel)
                        j.boitEau();
                }
                break;
            case TEMPETE_SE_DECHAINE:
                niveau_tempete++;
                break;
            case VENT_SOUFFLE:
                deplaceTempete(carteTempete.getdir(), carteTempete.getDistance());
                break;
            default:
                notifyObservers();
        }
    }
    public void clicExploration(){
        Case explore = Case.getCaseById(joueurJouant.getIdPositionCase());
        if(this.joueurJouant.getAction() == 0 ||                  //si joueur ne peut plus rien faire
           explore.get_explored() || // si la case a deja etait explore
           explore.getNb_sable() != 0) //si la case est ensable
            return;
        if(explore.getType() == Case.CaseType.Point_eau){
            for(Joueur j : listJoueur){
                if (j.getIdPositionCase() == explore.getIdCase())
                    j.ramasseEau();
            }
        }
        explore.explore();
        joueurJouant.depenseAction();
        if(explore.get_equipment()){
            freezeForEquipement = true;
        }
        int indexType = explore.getType().ordinal();
        if(indexType<8){
            int typePiece = indexType%4;
            if(pieceTrouvable.get(typePiece) == -1){
                pieceTrouvable.set(typePiece, explore.getIdCase());
            }else{
                Case indice = Case.getCaseById(pieceTrouvable.get(typePiece));
                Coordonne ce = Coordonne.from1Dto2D(plateau.indexOf(explore));
                Coordonne ci = Coordonne.from1Dto2D(plateau.indexOf(indice));
                int indexPiece;
                if(explore.getType().isColonne()){
                    indexPiece = Coordonne.from2Dto1D(ci.getLign(), ce.getColonne());
                }else{
                    indexPiece = Coordonne.from2Dto1D(ce.getLign(), ci.getColonne());
                }

                Case Adecouvrire = this.plateau.get(indexPiece);
                Adecouvrire.addPieceMachine(typePiece);
                this.pieceTrouvable.set(typePiece, 25+Adecouvrire.getIdCase());
            }
        }
        notifyObservers();
    }


    public void clicEquipement(){
        freezeForEquipement = false;
        piocheEquipement = true;
        Case positionJoueur = Case.getCaseById(this.joueurJouant.getIdPositionCase());
        if(positionJoueur.get_equipment() && positionJoueur.get_explored()){
            positionJoueur.takeEquipement();
            carteEquipement.geneCarteEquipement();
            CarteEquipement.TypeEquipement gear = carteEquipement.getCarte();
            pioche = gear;
            this.joueurJouant.ramasseEquipement(gear);
        }
        notifyObservers();
        piocheEquipement = false;
    }
    public void clicPrendrePiece(){
        int id = joueurJouant.getIdPositionCase();
        Case c = Case.getCaseById(id);
        int typePiece = c.prendPiece();
        joueurJouant.depenseAction();
        pieceTrouvable.set(typePiece, id + 50);
        pieceTrouve.set(typePiece, true);
        notifyObservers();

    }
    public void clicFinTour(){
        if(nombreCarteTempete != 0 && joueurJouant.getAction() != 0)
            return;
        joueurJouant.FinTour();
        indexJoueur++;
        if(indexJoueur == listJoueur.size()){
            indexJoueur = 0;
        }
        joueurJouant = listJoueur.get(indexJoueur);
        nombreCarteTempete = niveau_tempete;
        notifyObservers();
    }

    public void clicEau(int id_donneur){
        this.receveurPossible = new ArrayList<>();
        Joueur j = listJoueur.get(id_donneur);
        int idCase = j.getIdPositionCase();
        for(Joueur joueur : listJoueur){
            if(joueur.getIdPositionCase() == idCase && joueur != j){
                this.receveurPossible.add(joueur);
            }
        }
        notifyObservers();
    }
    public void echangeEau(int donner, int receveur){
        Joueur doneur = listJoueur.get(donner);
        Joueur recev = listJoueur.get(receveur);
        doneur.donneEau(recev);
        notifyObservers();
    }

    public void clicInventaire(int idJ, int idI){
        Joueur j = listJoueur.get(idJ);
        CarteEquipement.TypeEquipement c = CarteEquipement.TypeEquipement.values()[idI];
        switch (c){
            case BLASTER -> blaster = true;
            case JETPACK -> jetpack = true;
            default -> j.utiliseEquipement(c);
        }
    }
}

