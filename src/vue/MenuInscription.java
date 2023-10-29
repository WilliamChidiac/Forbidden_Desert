package vue;

import IG.Button;
import IG.Fenetre;

import IG.Texte;
import Observer.Observer;
import controlleur.ControlleurDebutJeu;
import controlleur.ControlleurJeu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;


public class MenuInscription extends JPanel implements Observer{
    private JTextField nomJoueur;
    private ButtonJoueurSuivant suivant;
    private FenetreDesciptionJoueur fentreDescription;
    private Fenetre fenetrePrincipal;
    private boolean aventurierPressable = true;

    /**
     * panneau qui aura le format de celle du menu d'inscription
     * @param fenetre la fenetre sur laquelle elle s'affichera
     */
    public MenuInscription(Fenetre fenetre){
        ControlleurDebutJeu.getPaquetAventurier().addObserver(this);
        fentreDescription = new FenetreDesciptionJoueur();
        Texte texte = new Texte("Nom du Joueur:");
        this.fenetrePrincipal = fenetre;
        this.add(texte);
        this.nomJoueur = new JTextField();
        this.nomJoueur.setColumns(30);
        this.nomJoueur.setText("");
        this.suivant = new ButtonJoueurSuivant(fenetre);
        VuePaquetAventurier paquetJoueur = new VuePaquetAventurier();
        this.add(this.nomJoueur);
        this.add(paquetJoueur);
        this.add(this.suivant);
        this.setVisible(true);

        nomJoueur.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if(nomJoueur.getText().trim() != "" && !aventurierPressable){
                    suivant.active();
                }
            }
            public void removeUpdate(DocumentEvent e) {
                if(nomJoueur.getText().trim() != "" && !aventurierPressable){
                    suivant.active();
                }
            }
            public void changedUpdate(DocumentEvent e) {}});


    }

    /**
     * recupere le text qui se trouve dans le text field [le nom du joueur]
     */
    public String getTextFieldValue(){
        return this.nomJoueur.getText();
    }

    /**
     * fonction update du menu d'inscription. Il rend le bouton suivant pressable ou pas et affiche
     * la descrition de l'aventurier donné au hasard
     */
    public void update(){
        boolean aventurier = ControlleurDebutJeu.getPaquetAventurier().isBouttonAventurier();
        boolean suivant = ControlleurDebutJeu.getPaquetAventurier().isSuivant();
        if(suivant) {
            aventurierPressable = true;
            nomJoueur.setText("");
        }else if(aventurier){
            String desc = ControlleurDebutJeu.getPaquetAventurier().getDesciption();
            fentreDescription.setDescription(desc);
            fentreDescription.setVisible(true);
        }


    }



    /**
     * class representant le boutton suivant qui permettra l'inscription d'un nouveau joueur
     */
    class ButtonJoueurSuivant extends Button implements Observer{

        /**
         * constructor qui construit permet d'ajouter un boutton suivant qui servira passe a la prochaine incription
         * @param fenetre la fenetre qui contient le bouton
         */
        public ButtonJoueurSuivant(Fenetre fenetre){
            super("Suivant", fenetre);
            this.desactive();
            ControlleurDebutJeu.getPaquetAventurier().addObserver(this);

        }
        /**
         * reinitialise les composante du menu d'inscription
         */
        public void clicGauche(){
            ControlleurDebutJeu.clicSuivant(getTextFieldValue());
        }
        /**
         * fonction update du bouton suivant. Il s'active si les joueurs ont fini de remplir
         * leurs noms et de choisir leur carte et emmene vers la fenetre du jeu si appuyé
         */
        public void update(){
            if (ControlleurDebutJeu.getPaquetAventurier().getMenu()){
                VueJeu menuPrincipal = new VueJeu(fenetrePrincipal, ControlleurJeu.getDesert());
                fenetrePrincipal.ajouteElement(menuPrincipal, "menu jeu");
                fenetrePrincipal.dessinePanel("menu jeu");
            }else if (getTextFieldValue().trim() != "" && !aventurierPressable){
               this.active();
            }else{
                this.desactive();
            }
        }

    }


    class VuePaquetAventurier extends Carte{
        public VuePaquetAventurier(){
            super(100, 200, Color.GREEN, "<html>Paquet<br>Aventurier</html>");
        }
        /**
         * si le boutton n'a pas ete presse, on pioche une carte
         * et active le bouton suivant du panneau si le textfield n'est pas vide
         */
        public void clicGauche(){
            if (aventurierPressable){
                aventurierPressable = false;
                ControlleurDebutJeu.clicAventurier();
            }
        }


        public void clicDroit(){}
    }
    class FenetreDesciptionJoueur extends Fenetre{
        private Texte description = new Texte("");
        public FenetreDesciptionJoueur(){
            super("descriptoin Aventurier", 600, 400);
            JPanel pan = new JPanel();
            pan.add(description);
            this.ajouteElement(pan, "desc");
            this.dessineFenetre();
            this.dessinePanel("desc");
            this.setPreferredSize(new Dimension(400, 300));
            this.setVisible(false);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    // set the window to invisible instead of disposing it
                    setVisible(false);
                }
            });
        }
        public void setDescription(String desc){
            this.description.changeTexte(desc);
            revalidate();
            repaint();
        }

    }




}






