package vue;

import IG.Button;
import IG.Fenetre;
import IG.Texte;
import controlleur.ControlleurJeu;
import modele.Joueur;

import javax.swing.*;
import java.awt.*;

public class VueCarteJoueur extends Carte  {
    private VueInventaire listInventaire;
    private Texte Actions;
    private Texte niv_eau;
    private Bouton_Inv inventaire;
    private Bouton_deau donne_eau;
    private Color couleurA;


    public VueCarteJoueur(Joueur j, int i){
        super(170,200,j.getAventurier().getCouleur(), "",20);
        couleurA = j.getAventurier().getCouleur();
        this.Actions = new Texte("action: 4\n");
        this.niv_eau = new Texte("niveau d'eau: "+j.getNiv_eau());
        Texte nom = new Texte("nom joueur: "+j.getNom());
        Texte aventurier= new Texte("aventurier: "+ j.getAventurier().getNomAventurier());
        inventaire = new Bouton_Inv(0);
        donne_eau = new Bouton_deau();
        listInventaire = new VueInventaire(i);
        this.add(nom);
        this.add(aventurier);
        this.add(Actions);
        this.add(niv_eau);
        this.add(donne_eau);
        this.add(inventaire);

    }
    /**
     * setters du niveau d'eau et du nombre d'actions
     */
    public void setNiv_eau(int n){this.niv_eau.changeTexte("niveau d'eau: "+n);}
    public void setActions(int n){
        this.Actions.changeTexte("Actions: "+n);
    }
    public void changeNombreInventaire(int n){
        this.inventaire.changeNombre(n);
        if (n == 0)
            this.inventaire.desactive();
        else
            this.inventaire.active();
    }
    public void ajouteInventaire(int ii){
        ((VueInventaire.CarteInventaire)listInventaire.affiche.getComponent(ii)).ajoute();
    }

    class Bouton_Inv extends Button{
        public Bouton_Inv(int n){
            super("Inventaire(0)");
            this.desactive();
        }
        public void changeNombre(int n){this.setText("Invetaire("+n+")");}
        public void clicGauche() {
            listInventaire.setVisible(true);
        }
    }
    class Bouton_deau extends Button{
        public Bouton_deau(){
            super("donner eau");
            this.BoutonVisible();
        }
        public void BoutonVisible(){
            this.setVisible(true);
        }
        public void BoutonInvisible(){
            this.setVisible(false);
        }

        public void clicGauche() {
            VueJoueurs vj = (VueJoueurs) this.getParent().getParent();
            int idJoeur = vj.getComponentZOrder(this.getParent());
            ControlleurJeu.clicEau(idJoeur);
        }
    }



    public void clicGauche() {

    }

    public void clicDroit() {

    }
}
