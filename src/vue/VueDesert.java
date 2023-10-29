package vue;

import IG.Fenetre;
import IG.Grille;
import Observer.Observer;
import modele.Case;
import modele.Desert;

import java.awt.*;
import java.util.ArrayList;


public class VueDesert extends Grille implements Observer {
    private Desert plateau;
    private Fenetre fenetre;
    public VueDesert(Fenetre fenetre, Desert d){
        super(5, 5, 2, 2);
        this.fenetre = fenetre;
        this.plateau = d;
        this.plateau.addObserver(this);
        ArrayList<Color> jc = this.plateau.getJoueurColor();
        VueCase.setPerso(jc);
        ArrayList<Case> map = plateau.getPlateau();
        int idcrash = plateau.getJoueurJouant().getIdPositionCase();
        for(Case c : map){
            VueCase vc = new VueCase(c);
            this.add(vc);
        }
        VueCase crash = VueCase.getVueCaseById(idcrash);
        for(int i = 0; i<jc.size(); i++){
            crash.ajouteAventurier(i);
        }
        setMaximumSize(new Dimension(755, 755));

        update();
    }
    /**
     * Cette fonction échange la position de deux éléments (VueCase) dans un conteneur à l'aide
     * de leurs index respectifs dans le conteneur. La fonction récupère les deux éléments en question
     * à l'aide de leurs index, puis les ajoute de nouveau au conteneur dans l'ordre inverse,
     * ce qui a pour effet d'inverser leur position.
     **/
    public void swapVueCase(int index1, int index2){
        VueCase vc1 = (VueCase) this.getComponent(index1);
        VueCase vc2 = (VueCase) this.getComponent(index2);
        this.add(vc2, index1);
        this.add(vc1, index2);
    }
    /**
     * La fonction "update" met à jour l'état graphique de la vue du désert en fonction des changements survenus
     * dans le modèle de plateau. Elle met à jour la position des aventuriers, les cases adjacentes révélées,
     * la position de la tempête et l'état des pièces à collecter.
     * **/
    public void update(){
        int idNewCase = plateau.getJoueurJouant().getIdPositionCase();
        int idAncienneCase = plateau.getJoueurJouant().getIdPositionCasePrecedente();
        VueCase vc1 = VueCase.getVueCaseById(idAncienneCase);
        VueCase vc2 = VueCase.getVueCaseById(idNewCase);
        int indexJoueur = plateau.getIndexJoueur();
        vc1.retireAventurier(indexJoueur);
        vc2.ajouteAventurier(indexJoueur);      //change place aventurier
        ArrayList<Integer> caseAdja = plateau.getCaseAdjacente();
        if(Case.getCaseById(idNewCase).getType() == Case.CaseType.Tunnel &&
           Case.getCaseById(idNewCase).get_explored()){
            caseAdja.addAll(this.plateau.getTunnelExplore());
        }
        if (plateau.isJetpack()){
            for(int i = 0; i<24; i++){
                caseAdja.add(i);
            }
            plateau.resetJetpack();
        }
        for (int id = 0; id < 25; id++) {
            VueCase vc = VueCase.getVueCaseById(id);
            if (caseAdja.contains(vc.getVueCaseId())) {
                Case c = Case.getCaseById(id);
                if(c.get_explored())    // revele une carte si elle a ete explore
                    vc.decouvreCarte(); //
                int nb_sable = c.getNb_sable(); // change sable si desable
                vc.setSable(nb_sable);
                switch (nb_sable) {//change couleur case dependament de la place de l'aventurier
                    case 0:
                        vc.changeCouleur(Color.MAGENTA);
                        break; case 1: vc.changeCouleur(Color.pink);
                        break; case 2: vc.changeCouleur(Color.red);
                        break;
                }
            } else {
                vc.changeCouleur(Color.ORANGE);
            }
        }
        if(this.plateau.isBougeTempete()) {
            int ancienTmp = this.plateau.getIndexCaseModifier();            //
            int newTmp = this.plateau.getIndexTempete();                    //
            VueCase vc = (VueCase) this.getComponent(newTmp);               // change la place de la tempete quand vent souffle
            int sable = plateau.getPlateau().get(ancienTmp).getNb_sable();  // et incremente sable
            vc.setSable(sable);                                             //
            this.swapVueCase(ancienTmp, newTmp);                            //
            this.plateau.tempeteBougee() ;                                   //
        }
        ArrayList<Integer> pieceTrouvable = this.plateau.getPieceTrouvable();   //
        for(int i = 0; i < 4; i++){
            if (pieceTrouvable.get(i) >= 50){
                int id = pieceTrouvable.get(i)-50;
                VueCase vc = VueCase.getVueCaseById(id);
                vc.ramassePiece(i);
            }else if(pieceTrouvable.get(i) > 24){                               //place les piece machine dans leur
                int id = pieceTrouvable.get(i)-25;                              //si elle ont etait decouverte
                VueCase vc = VueCase.getVueCaseById(id);                        //
                vc.metPiece(i);                                                 //
            }                                                                   //
        }                                                                       //
    }
}