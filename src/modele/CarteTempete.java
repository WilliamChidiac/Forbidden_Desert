package modele;

import Observer.Observable;

import java.util.Random;


public class CarteTempete extends Observable {
    private TypeCarteT carte;
    private Typedirection dir;
    private int distance;
    public CarteTempete(){}

    public TypeCarteT getCarte(){return carte;};
    public int getDistance(){return distance;}
    public Coordonne getdir(){return dir.getCoordonne();}
    public String getBoussol(){return dir.getDirection();}
    public void genereCarteTempete(){
        randomCard();
        switch (carte){
            case VENT_SOUFFLE:
                randomVentSouffle();
            default:
                notifyObservers();
        }
    }

    private void randomCard(){
        float rnd = new Random().nextFloat();
        if (rnd < 0.7)
            carte = TypeCarteT.VENT_SOUFFLE;
        else if(rnd <0.85){
            carte = TypeCarteT.VAGUE_DE_CHALEUR;
        }else {
            carte = TypeCarteT.TEMPETE_SE_DECHAINE;
        }
    }
    private void randomVentSouffle(){
        int rnddir = new Random().nextInt(4);
        int rnddist = rnddir%3 + 1;
        dir = Typedirection.values()[rnddir];
        distance = rnddist;
    }


    public enum Typedirection{
        NORD(-1, 0, "Nord"),
        WEST(0, 1, "West"),
        SUD(1, 0, "Sud"),
        EST(0, -1, "Est");
        private int x;
        private int y;
        private String direction;
        Typedirection(int x, int y,String nom){
            this.x = x;
            this.y = y;
            this.direction = nom;
        }
        public Coordonne getCoordonne(){return new Coordonne(x, y);}
        public String getDirection(){return this.direction;}
    }
    public enum TypeCarteT{
        VENT_SOUFFLE("Le vent souffle"),
        VAGUE_DE_CHALEUR("Vague de chaleur"),
        TEMPETE_SE_DECHAINE("la tempete se dechaine");

        private String nom;
        TypeCarteT(String s){this.nom = s;}
        public String getType(){return this.nom;}
    }
}