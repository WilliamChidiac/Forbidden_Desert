package vue;

import IG.Button;
import IG.Fenetre;
import Observer.Observer;
import controlleur.ControlleurJeu;
import modele.Case;
import modele.Desert;

import javax.swing.*;


public class VueBoutton extends JPanel implements Observer {
    private Desert desert;
    private BoutonExplore explore;
    private BoutonPrendrePiece prendrePiece;
    private BoutonFinTour finTour;
    public VueBoutton(Fenetre f, Desert d){
        this.desert = d;
        explore = new BoutonExplore(f);
        prendrePiece = new BoutonPrendrePiece(f);
        finTour = new BoutonFinTour(f);
        this.add(explore);
        this.add(prendrePiece);
        this.add(finTour);
        d.addObserver(this);
        update();
    }
    /**
     * methode qui active le bouton explore
     */
    public void activeExplore(){
        this.explore.active();
    }
    /**
     * methode qui active le bouton Prendre piece
     */
    public void activePrendrePiece(){
        this.prendrePiece.active();
    }
    /**
     * methode qui active le bouton Fin de tour
     */
    public void activeFinTour(){
        this.finTour.active();
    }
    /**
     * methode qui desactive le bouton explore
     */
    public void desactiveExplore(){
        this.explore.desactive();
    }
    /**
     * methode qui desactive le bouton Prendre piece
     */
    public void desactivePrendrePiece(){
        this.prendrePiece.desactive();
    }
    /**
     * methode qui desactive le bouton Fin de tour
     */
    public void desactiveFinTour(){
        this.finTour.desactive();
    }

    /**
     * fonction update des trois bouton explore, prendre piece et fin tour. Elle les acitve et ou desactive
     * en fonction de la partie
     */
    public void update() {
        if(desert.getJoueurJouant().getAction() == 0){
            if(desert.getNombreCarteTempete() == 0){
                this.activeFinTour();
            }
            this.desactiveExplore();
            this.desactivePrendrePiece();
            return;
        }
        this.desactiveFinTour();
        int id = desert.getJoueurJouant().getIdPositionCase();
        Case c = Case.getCaseById(id);
        if (c.getNb_sable() != 0){
            this.desactiveExplore();
            this.desactivePrendrePiece();
            return;
        }
        if (!c.get_explored()){
            this.activeExplore();
            this.desactivePrendrePiece();
            return;
        }
        if(c.hasPiece()){
            this.activePrendrePiece();
        }else{
            this.desactivePrendrePiece();
            this.desactiveExplore();
        }

    }

    class BoutonFinTour extends Button {
        public BoutonFinTour(Fenetre fenetre){
            super("Fin du tour",fenetre);
            this.desactive();
        }

        public void clicGauche() {
            ControlleurJeu.clicFinTour();
        }
    }
    class BoutonPrendrePiece extends Button {
        public BoutonPrendrePiece(Fenetre fenetre){
            super("Prendre pièce",fenetre);
            this.desactive();
        }

        public void clicGauche() {
            ControlleurJeu.clicPrendrePiece();
        }
    }
    class BoutonExplore extends Button{
        public BoutonExplore(Fenetre fenetre){
            super("explorer",fenetre);
            this.desactive();
        }

        public void clicGauche() {
            ControlleurJeu.clicExploration();
        }
    }
}
