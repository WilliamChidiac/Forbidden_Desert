package vue;

import IG.Fenetre;
import IG.Grille;
import IG.Texte;
import controlleur.ControlleurJeu;
import modele.CarteEquipement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VueInventaire extends Fenetre {
    Grille affiche = new Grille(1, 3, 2, 2);
    private int idJoueur;
    public VueInventaire(int i){
        super("Inventaire", 500, 500);
        idJoueur = i;
        ajouteElement(affiche, "me");
        dessinePanel("me");
        CarteInventaire carte1 = new CarteInventaire(CarteEquipement.TypeEquipement.BLASTER);
        CarteInventaire carte2 = new CarteInventaire(CarteEquipement.TypeEquipement.BOUCLIER_SOLAIRE);
        CarteInventaire carte3 = new CarteInventaire(CarteEquipement.TypeEquipement.JETPACK);
        affiche.add(carte1);
        affiche.add(carte2);
        affiche.add(carte3);
        dessineFenetre();
        setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // set the window to invisible instead of disposing it
                setVisible(false);
            }
        });
    }
    public int getIdJoueur(){return this.idJoueur;}
    public void ajoutecCarte(CarteEquipement.TypeEquipement carte){
        int index = carte.ordinal();
        CarteInventaire c = (CarteInventaire) this.affiche.getComponent(index);
        c.ajoute();
        c.setVisible(true);


    }
    public void utiliseCarte(CarteEquipement.TypeEquipement carte){
        int index = carte.ordinal();
        CarteInventaire c = (CarteInventaire) this.affiche.getComponent(index);
        c.enleve();
        if(c.nombre ==0){
            c.setVisible(false);
        }
    }


    class CarteInventaire extends Carte{
        private Texte nombreTexte = new Texte("vous en avez : 0");
        private int nombre;
        public CarteInventaire(CarteEquipement.TypeEquipement c){
            super(150,150,Color.BLUE, "",20);
            nombre = 0;
            Texte nom = new Texte(c.toString());
            Texte desc = new Texte(c.description());
            this.add(nom);
            this.add(nombreTexte);
            this.add(desc);
        }
        public void enleve(){nombre--;nombreTexte.changeTexte("vous avez : "+nombre);}
        public void ajoute(){
            nombre++;
            nombreTexte.changeTexte("vous avez : "+nombre);
        }

        public void clicGauche(){
            JPanel vj = (JPanel) this.getParent();
            enleve();
            int idInventaire = vj.getComponentZOrder(this);
            vj.setVisible(false);
            ControlleurJeu.utiliseInventaire(idInventaire);
        }

        public void clicDroit(){}

    }
}
