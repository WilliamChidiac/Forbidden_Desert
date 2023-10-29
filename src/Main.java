import java.awt.*;

import vue.*;
import IG.*;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre("test", Toolkit.getDefaultToolkit().getScreenSize().width, 1000);
        MenuNombreJoueur m = new MenuNombreJoueur(fenetre);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.ajouteElement(m, "menu debut");
        fenetre.dessineFenetre();
        fenetre.dessinePanel("menu debut");
    }
}


