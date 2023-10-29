package vue;

import IG.Fenetre;
import IG.Texte;
import Observer.Observer;
import modele.Desert;

import javax.swing.*;
import java.awt.*;


public class VueJeu extends JPanel implements Observer {
    private Fenetre fenetreJeu;
    private Desert d;
    private JPanel menu;

    public VueJeu(Fenetre f, Desert dd){
        VueFinJeu vueFinJeu = new VueFinJeu();
        menu = new JPanel();
        d = dd;fenetreJeu = f;
        VueJoueurs vueJoueurs = new VueJoueurs(d);
        VueInfoTempete vueinfotemp = new VueInfoTempete(d);
        VueCartesEquipement VC = new VueCartesEquipement();
        VueCarteDefausse vueCarteDefausse = new VueCarteDefausse(d.getCarteTempete());
        VueCartesTempete vueCartesTempete = new VueCartesTempete();
        VueDesert vuedesert = new VueDesert(fenetreJeu,d);
        VueBoutton vueBoutons = new VueBoutton(fenetreJeu,d);
        //contruction du layout
        menu.setLayout(new BoxLayout(menu,BoxLayout.X_AXIS));
        JPanel panelgauche = new JPanel();
        panelgauche.setLayout(new BoxLayout(panelgauche,BoxLayout.Y_AXIS));
        panelgauche.add(vueJoueurs);
        JPanel panelmilieu = new JPanel();
        panelmilieu.setLayout(new BoxLayout(panelmilieu,BoxLayout.Y_AXIS));
        Texte titre = new Texte("DESERT INTERDIT");
        titre.setFont(new Font("Crystal", Font.BOLD, 10));
        titre.setForeground(Color.ORANGE);
        panelmilieu.add(titre);
        panelmilieu.add(new Texte("<html>TROUVEZ CES QUATRES OBJETS :<br/>MOTEUR GOUVERNAIL HELICE ENERGIE<html>"));
        panelmilieu.add(vuedesert);
        JPanel paneldroit = new JPanel();
        paneldroit.setLayout(new BoxLayout(paneldroit, BoxLayout.Y_AXIS));
        paneldroit.add(vueCartesTempete);
        paneldroit.add(vueCarteDefausse);
        paneldroit.add(VC);
        paneldroit.add(vueinfotemp);
        paneldroit.add(vueBoutons);
        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        menu.add(panelgauche);
        menu.add(panelmilieu);
        menu.add(paneldroit);
        this.add(menu);
        d.addObserver(this);
    }
    public void update(){
        repaint();
        revalidate();
    }

}
