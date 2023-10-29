package modele;



/**
 * class coordonnee qui va permettre de pouvoir mieu interagir avec le layout grille surtout
 * puisque celuici est enfaite une "list" et non une matrice
 */
public class Coordonne {
    private int x;
    private int y;

    /**
     * constructeur null, rend la coordonne a l'origine
     */
    public Coordonne(){
        this(0, 0);
    }

    /**
     * Constructeur qui prend les coordonne i, j d'un object dans un tableau 2D,
     * tel que l'objet en question en soit egal a tableau2D[i][j]
     * @param x i
     * @param y j
     */
    public Coordonne(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getLign(){return x;}
    public int getColonne(){return y;}

    /**
     * somme une coordonne a l'objet
     * @param v la deuxieme coordonne
     */
    public void addVecteur(Coordonne v){
        this.x += v.x;
        this.y += v.y;
    }

    /**
     * transforme un index de tableau[25] on ses index dans un tableau2D[5][5]
     * On utilise surtout dans le contexte du desert
     * @param c l'index du tableau1D
     * @return l'index du tableau2D
     */
    public static Coordonne from1Dto2D(int c){
        int x = c/5;
        int y = c%5;
        return new Coordonne(x, y);
    }

    /**
     * transforme une pair d'index (i, j) d'un tableau2D en son index respectif dans un tableau1D
     * @param x pair.first
     * @param y pair.second
     * @return index1D
     */
    public static int from2Dto1D(int x, int y){
        return 5*x+y;
    }
    public int from2Dto1D(){
        return from2Dto1D(this.x, this.y);
    }

    public int ajouteVecteur1D(int i){
        Coordonne transforme = Coordonne.from1Dto2D(i);
        transforme.addVecteur(this);
        if (transforme.x < 0 || transforme.y < 0 || transforme.x > 4 || transforme.y > 4){
            throw new ArrayIndexOutOfBoundsException("la case de coordonne (" + this.x + ", " + this.y + ") est inexistante" );
        }else{
            return transforme.from2Dto1D();
        }

    }

}