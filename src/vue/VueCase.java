package vue;


import IG.Grille;
import IG.Texte;
import IG.ZoneCliquable;
import controlleur.ControlleurJeu;
import modele.Case;
import modele.Case.CaseType;
import modele.Desert;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VueCase extends ZoneCliquable /**implements Observer*/{
    private static ArrayList<Color> perso;
    private static HashMap<Integer, VueCase> idVueCase = new HashMap<>();

    public static void setPerso(ArrayList<Color> color){
        perso = color;
    }

    public static VueCase getVueCaseById(int id){return idVueCase.get(id);}
    private int id;
    private boolean Oasis;
    private Texte sable;
    private Texte nomCase;
    private Grille joueurs;
    private ArrayList<Texte> listeEquipement = new ArrayList<>();

    public VueCase(Case c){
        super("", 40, 40);
        this.setBackground(Color.ORANGE);
        this.nomCase = new Texte(c.getType().to_string());
        this.nomCase.setVisible(false);
        String sable = "Sable: " + c.getNb_sable();
        this.sable = new Texte(sable);
        this.add(this.sable);
        this.add(this.nomCase);
        for(Desert.PieceMachine p : Desert.PieceMachine.values()){
            Texte piece = new Texte(p.getPiece());
            piece.setVisible(false);
            listeEquipement.add(piece);
            this.add(piece);
        }
        this.joueurs = new Grille(2,3, 1, 1);
        this.joueurs.setBackground(Color.ORANGE);
        this.joueurs.setPreferredSize(new Dimension(20, 20));
        this.joueurs.setMaximumSize(new Dimension(30, 30));
        for(Color i : perso){
            CarreJoueur carre = new CarreJoueur(i);
            carre.setVisible(false);
            this.joueurs.add(carre);
        }
        this.add(this.joueurs);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Oasis = (c.getType() == CaseType.Point_eau || c.getType() == CaseType.Mirage);
        this.id = c.getIdCase();
        idVueCase.put(this.id, this);
        if (c.getType() == CaseType.OeilTemp){
            setVisible(false);
        }

    }
    /**
     *  Cette fonction retourne l'identifiant de la case.
     **/
    public int getVueCaseId(){return this.id;}
    /**
     *  Cette fonction prend en paramètre l'index d'un type d'équipement
     *  et rend visible l'objet graphique correspondant dans la liste des équipements.
     **/
    public void metPiece(int indexType){this.listeEquipement.get(indexType).setVisible(true);}
    /**
     *  Cette fonction prend en paramètre l'index d'un type d'équipement
     *  et rend invisible l'objet graphique correspondant dans la liste des équipements.
     **/
    public void ramassePiece(int indexType){this.listeEquipement.get(indexType).setVisible(false);}
    /**
     *  Cette fonction est une méthode de la classe Swing qui est appelée automatiquement lorsque le composant doit être redessiné.
     *  Dans ce cas, elle dessine une forme ovale bleue au centre de la case si la variable "Oasis" est vraie.
     **/
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (Oasis) {
            g.setColor(Color.BLUE);
            g.fillOval(getWidth()/2+5, getHeight()/2, 25, 25);
        }
    }
    /**
     *  Cette fonction rend visible le nom de la case.
     **/
    public void decouvreCarte(){
        this.nomCase.setVisible(true);
    }
    /**
     *  Cette fonction prend en paramètre un nombre de sable
     *  et met à jour le texte de l'objet "sable" avec ce nombre.
     **/
    public void setSable(int nombreSable) {
        this.sable.changeTexte("sable: "+nombreSable);
    }
    /**
     *   Cette fonction prend en paramètre l'index d'un joueur
     *   et rend invisible l'objet graphique correspondant dans la liste des joueurs.
     **/
    public void retireAventurier(int i){
        this.joueurs.getComponent(i).setVisible(false);
    }
    /**
     *  Cette fonction prend en paramètre l'index d'un joueur et rend visible
     *  l'objet graphique correspondant dans la liste des joueurs.
     **/
    public void ajouteAventurier(int i){
        this.joueurs.getComponent(i).setVisible(true);
    }
    /**
     *  Cette fonction prend en paramètre une couleur et met à jour la couleur de fond de l'objet graphique courant
     *  ainsi que la couleur de fond des objets graphiques enfants (ici, la liste des joueurs).
     **/
    public void changeCouleur(Color c){
        this.setBackground(c);
        this.joueurs.setBackground(c);
    }


    public void clicGauche() {ControlleurJeu.clicGaucheCase(this.id);}
    public void clicDroit(){ControlleurJeu.clicDroitCase(this.id);}
}

class CarreJoueur extends ZoneCliquable{
    Color couleur;
    public CarreJoueur(Color couleurJoueur){
        super("", 5, 5);
        setPreferredSize(new Dimension(5, 5));
        this.couleur = couleurJoueur;
        this.setBackground(this.couleur);
    }

    public void clicGauche(){}
    public void clicDroit(){}
}


