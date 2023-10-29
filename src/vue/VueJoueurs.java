package vue;


import IG.*;
import Observer.Observer;
import controlleur.ControlleurJeu;
import modele.Desert;
import modele.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VueJoueurs extends JPanel implements Observer {
    private Desert desert;
    private Fenetre fenetreEau;
    private JPanel affiche;

    public VueJoueurs(Desert d){
        fenetreEau = new Fenetre("a qui", 700, 500);

        affiche = new Grille(3, 2, 3, 3);
        fenetreEau.setSize(700,50);
        desert = d;
        ArrayList<Joueur> J = d.getListJoueur();
        if(J.size()>=3){
            this.setLayout(new GridLayout(J.size()/2, 2, 2, 2));
        }else{
            this.setLayout(new GridLayout(J.size(), 1, 2, 2));
        }
        int i = 0;
        for(Joueur j : J){
            VueCarteJoueur vj = new VueCarteJoueur(j, i);
            i++;
            this.add(vj);
            affiche.add(new ChoixEau(j));

        }
        fenetreEau.ajouteElement(affiche, "a qui");
        fenetreEau.dessineFenetre();
        fenetreEau.dessinePanel("a qui");
        fenetreEau.setVisible(false);
        desert.addObserver(this);
        ControlleurJeu.setVueJoueur(this);

    }
    public void setV(boolean b){this.fenetreEau.setVisible(b);}

    class ChoixEau extends Carte {
        private Texte nom = new Texte("");
        private Texte aventurier = new Texte("");
        private Texte niv_eau = new Texte("");
        /**Cette fonction crée une instance de la classe "ChoixEau" qui hérite de la classe "Vue".
         * Elle prend en paramètre un objet "Joueur" et crée une fenêtre graphique affichant le nom du joueur,
         * le nom de l'aventurier associé au joueur et le niveau d'eau actuel. Ces informations sont affichées
         * à l'aide de trois composants "Texte" ajoutés à la fenêtre "ChoixEau".
         * **/
        public ChoixEau(Joueur j){
            super(100, 100, j.getAventurier().getCouleur(), "");
            nom.changeTexte("nom joueur:"+j.getNom());
            aventurier.changeTexte("nom aventurier: "+j.getAventurier().getNomAventurier());
            niv_eau.changeTexte("niveau eau: " + j.getNiv_eau());
            this.add(nom);
            this.add(aventurier);
            this.add(niv_eau);
        }
        public void clicGauche() {
            JPanel vj = (JPanel) this.getParent();
            int idJoeur = vj.getComponentZOrder(this);
            ControlleurJeu.clicReceveur(idJoeur);
        }
        public void clicDroit(){}
    }
    /**
     * La fonction "update" met à jour les vues des joueurs en fonction de leurs actions et de leur niveau d'eau.
     * Elle récupère également la liste des joueurs pouvant recevoir une carte de trésor
     * et les affiche sur les vues correspondantes. Enfin, elle met à jour les vues de chaque joueur
     * en modifiant les valeurs de leur niveau d'eau et du nombre d'actions restantes.
     **/
    public void update(){
        int ij = desert.getIndexJoueur();
        if(desert.isPiocheEquipement()){
            VueCarteJoueur vj = (VueCarteJoueur) this.getComponent(ij);
            vj.ajouteInventaire(0);
        }
        ArrayList<Joueur> possible = desert.getReceveurPossible();
        ArrayList<Joueur> lj = desert.getListJoueur();
        for(int i = 0; i< lj.size(); i++){
            Joueur j = lj.get(i);
            VueCarteJoueur vj = (VueCarteJoueur) this.getComponent(i);
            int n = j.getNombreInventaire();
            vj.changeNombreInventaire(n);
            ChoixEau ce = (ChoixEau) this.affiche.getComponent(i);
            int nb_action = j.getAction();
            int niv_eau = j.getNiv_eau();
            vj.setActions(nb_action);
            vj.setNiv_eau(niv_eau);
            ce.niv_eau.changeTexte("niveau eau: "+niv_eau);
            if (possible.contains(j)){
                this.affiche.getComponent(i).setVisible(true);
            }else{
                this.affiche.getComponent(i).setVisible(false);
            }
        }

    }


}
