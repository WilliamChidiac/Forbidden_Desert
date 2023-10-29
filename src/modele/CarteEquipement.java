package modele;


import java.util.Random;

public class CarteEquipement {
    private TypeEquipement carte;
    public CarteEquipement(){}
    public void geneCarteEquipement(){
        int rnd = new Random().nextInt(3);
        this.carte = TypeEquipement.values()[rnd];
    }
    public TypeEquipement getCarte(){return this.carte;}

    public enum TypeEquipement{
        BLASTER,
        BOUCLIER_SOLAIRE,
        JETPACK;
        public String description(){
            switch(this){
                case BLASTER :
                    return "Permet de retirer le sable dans les cases adjacentes";
                case BOUCLIER_SOLAIRE:
                    return "Vous protège d'une vague de chaleur";
                case JETPACK:
                    return "Permet de vous déplacer dans n'importe quelle case du désert (une fois)";
                default:
                    return "erreur";
            }
        }
    }
}