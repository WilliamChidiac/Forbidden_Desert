package vue;

import javax.swing.*;

import IG.Texte;
import Observer.Observer;
import modele.Desert;

import java.awt.*;

public class VueInfoTempete extends JPanel implements Observer {
    private Desert d;
    private JLabel nomBarre;
    private Texte tot_sable;
    private JProgressBar progresTempete;

    public VueInfoTempete(Desert de) {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.d = de;
        this.tot_sable = new Texte("NOMBRE TOTAL DE SABLE : 8");
        this.nomBarre = new JLabel("Niveau de la tempête");
        this.add(tot_sable);
        this.add(this.nomBarre);
        this.progresTempete = new JProgressBar();
        this.progresTempete.setPreferredSize(new Dimension(60,10));
        this.progresTempete.setMinimum(0);
        this.progresTempete.setMaximum(7);
        this.progresTempete.setStringPainted(true);
        this.progresTempete.setValue(2);
        this.progresTempete.setString(2 + "/7");
        this.add(this.progresTempete);
        d.addObserver(this);
    }
    /**
     * Cette fonction met à jour les éléments graphiques de la vue principale du jeu en fonction des données
     * du modèle. Elle modifie le texte d'un élément tot_sable pour afficher le nombre total de sable dans
     * le désert, met à jour la barre de progression progresTempete pour refléter le niveau
     * de la tempête actuelle, et met à jour la chaîne de texte associée à cette barre.
     **/
    public void update() {
        this.tot_sable.changeTexte("NOMBRE TOTALE DE SABLE " + d.getTotal_sable());
        this.progresTempete.setValue(d.getNiveau_tempete());
        this.progresTempete.setString(d.getNiveau_tempete()+ "/7");
    }

}