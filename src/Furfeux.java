import javax.swing.*;

public class Furfeux {
    Terrain terrain;
    Joueur joueur;


    public Furfeux(String f) {
        this.terrain = new Terrain(f);
        this.joueur = terrain.getJoueur();
    }
    public void tour() {
        terrain.propagateFire();
        joueur.DecrementeResistance(joueur.getC().getChaleur());
    }

    /**
     * Obtient la liste des cases traversables voisines aux coordonnées spécifiées.
     * @return true si le joueur est sur la case Sortie(gagne) ou si cas resitance atteind 0(perd).
     */
    public boolean partieFinie() {
        return joueur.getC() instanceof Sortie || joueur.getResistance() <= 0;
    }
    public static void main(String[] args) {
        int tempo = 100;
        Furfeux jeu = new Furfeux("src/manoir.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            jeu.tour();
            graphic.repaint();

            //Si la partie est finie soit on est dans l'ecran de partie ganger soit celle ou le joueur a perdu
            if (jeu.partieFinie()) {
                if (jeu.joueur.getResistance() > 0) {
                    graphic.ecranFinalWin(Math.max(0, jeu.joueur.getResistance()));
                    ((Timer) e.getSource()).stop();
                }
                else {
                    graphic.ecranFinalLost(Math.max(0, jeu.joueur.getResistance()));
                    ((Timer) e.getSource()).stop();

                }
            }
        });
        timer.start();
    }
}
