package modele;

import java.awt.*;
import java.util.ArrayList;

public class Aventurier {
    private TypeAventurier nomAventurier;
    private int max_eau;
    private Color couleur;
    private ArrayList<Coordonne> deplacement;
    private ArrayList<Coordonne> donneEau;
    private String description;

    /**
     * constructeur qui un creer l'aventurier associe au nom donne
     * @param nomA le nom de l'aventurier
     */
    public Aventurier(TypeAventurier nomA){
        this.nomAventurier = nomA;
        this.max_eau = 4;
        this.deplacement = new ArrayList<Coordonne>();
        this.donneEau = new ArrayList<>();
        this.description = "vous avez pioche ";
        this.deplacement.add(new Coordonne());
        this.deplacement.add(new Coordonne(0, 1));
        this.deplacement.add(new Coordonne(0, -1));
        this.deplacement.add(new Coordonne(1, 0));
        this.deplacement.add(new Coordonne(-1, 0));

        this.donneEau.add(new Coordonne(0, 0));
        switch (this.nomAventurier){
            case PORTEUSE:
                this.couleur = Color.blue;
                this.max_eau = 5;
                this.donneEau.add(new Coordonne(0, 1));
                this.donneEau.add(new Coordonne(0, -1));
                this.donneEau.add(new Coordonne(1, 0));
                this.donneEau.add(new Coordonne(-1, 0));
                this.description += "la porteuse d'eau";
                break;
            case Explorateur:
                this.couleur = Color.green;
                this.deplacement.add(new Coordonne(1, 1));
                this.deplacement.add(new Coordonne(1, -1));
                this.deplacement.add(new Coordonne(-1, 1));
                this.deplacement.add(new Coordonne(-1, -1));
                this.description += "l'explorateur";
                break;
            case ARCHEOLOGUE:
                this.couleur = Color.red;
                this.max_eau = 3;
                this.description += "l'archeologue";
                break;
            case ALPINISTE:
                this.couleur = Color.black;
                this.max_eau = 3;
                this.description += "alpiniste";
                break;
            case METEOROLOGUE:
                this.couleur = Color.gray;
                this.description += "le meteorologue";
                break;
            case NAVIGATRICE :
                this.couleur = Color.yellow;
                this.description = "la navigatrice";
                break;
        }
    }
    public String getDescription(){return this.description;}
    /**
     * @return les Coordonne de deplacement possible, pour la plus part se sont les Coordonne vertical et horizontal mais
     * pour l'explorateur il aussi les Coordonne diagoanal, ex (1, 1)
     */
    public ArrayList<Coordonne> getDeplacement(){
        return this.deplacement;
    }

    /**
     * @return les Coordonne de case au quel on peut donne de l'eau, pour la plus part le resultat est le vect (0, 0)
     * mais pas pour la porteuse d'eau;
     */
    public ArrayList<Coordonne> getDonneEau(){
        return this.donneEau;
    }

    /**
     * @return la capacite maximal d'eau qu'un aventurier peut porter
     */
    public int getMax_eau(){
        return this.max_eau;
    }

    /**
     * @return la couleur associe a l'aventurier
     */
    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * @return le nom de l'aventurier
     */
    public String getNomAventurier(){return this.nomAventurier.nomA;}

    enum TypeAventurier{
        PORTEUSE("la porteuse d'eau"),
        ARCHEOLOGUE("l'archeologue"),
        ALPINISTE("l'alpiniste"),
        Explorateur("l'explorateur"),
        METEOROLOGUE("le meteorologue"),
        NAVIGATRICE("la navigatrice");

        private String nomA;
        TypeAventurier(String nom){
            this.nomA = nom;
        }
    }
}

