import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Terrain {
    /* ATTRIBUTS */
    private int hauteur, largeur;
    private Case[][] carte;
    private Joueur joueur;


    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe Terrain.
     *
     * @param file Le chemin vers le fichier de configuration du terrain.
     */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            int resistanceJoueur = sc.nextInt();
            int cles = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new Mur(l, c); break;
                        case ' ': cc = new Hall(l, c); break;
                        case '+': cc = new Hall(l, c, true); break;
                        case '1': case '2': case '3': case '4':
                            cc = new Hall(l, c, (int)ch-(int)'0'); break;
                        case 'O': cc = new Sortie(l, c, 0); break;
                        case '@': cc = new Porte(l, c, false); break;
                        case '.': cc = new Porte(l, c, true); break;
                        case 'H':
                            if (this.joueur != null) throw new IllegalArgumentException("carte avec deux joueurs");
                            cc = new Hall(l, c);
                            this.joueur = new Joueur((CaseTraversable) cc, resistanceJoueur, cles);
                            ((Hall) cc).entre(joueur);
                            System.out.println("Avant jeu,vous avez: " + cles + " cles");
                            break;
                        default:  cc = null; break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }
        catch (IOException e) { e.printStackTrace(); System.exit(1); }
    }


    /* METHODES */
    /**
     * Obtient la case située aux coordonnées spécifiées.
     *
     * @param lig Ligne de la case.
     * @param col Colonne de la case.
     * @return La case aux coordonnées spécifiées.
     * @throws ArrayIndexOutOfBoundsException Si les coordonnées sortent du terrain.
     */
    public Case getCase(int lig,int col){
        if(lig > hauteur || lig < 0 || col > largeur || col < 0 ){
            throw new ArrayIndexOutOfBoundsException("La case sorte du terrain!");
        }else {
            return carte[lig][col];
        }
    }

    /**
     * Obtient le joueur actuellement sur le terrain.
     *
     * @return Le joueur.
     */
    public Joueur getJoueur() { return this.joueur; }

    /**
     * Obtient la hauteur du terrain.
     *
     * @return La hauteur du terrain.
     */
    public int getHauteur() { return hauteur; }

    /**
     * Obtient la largeur du terrain.
     *
     * @return La largeur du terrain.
     */
    public int getLargeur(){ return largeur; }

    /**
     * Obtient la liste des cases traversables voisines aux coordonnées spécifiées.
     * @param lig Ligne de la case.
     * @param col Colonne de la case.
     * @return La liste des cases traversables voisines.
     */
    public ArrayList<CaseTraversable> getVoisinesTraversables(int lig, int col) {
        ArrayList<CaseTraversable> voisinesTraversables = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int newRow = lig + i;
                int newCol = col + j;

                if (newRow >= 0 && newRow < hauteur && newCol >= 0 && newCol < largeur) {
                    Case neighbor = carte[newRow][newCol];

                    if (neighbor instanceof CaseTraversable) {
                        voisinesTraversables.add((CaseTraversable) neighbor);
                    }
                }
            }
        }
        return voisinesTraversables;
    }

    /**
     * Propage le feu sur le terrain en fonction des règles spécifiées.
     */
    public void propagateFire() {
        Random random = new Random();
        for (int l = 0; l < hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                Case currentCase = carte[l][c];
                if (currentCase instanceof CaseTraversable currentTraversable) {

                    ArrayList<CaseTraversable> voisinesTraversables = getVoisinesTraversables(l, c);

                    int totalHeat = currentTraversable.calculateTotalHeat(voisinesTraversables);
                    int randomNumber = random.nextInt(200); //200

                    if (randomNumber < totalHeat) {
                        currentTraversable.incrementeChaleur();
                        //System.out.println("Inc " + currentCase.getLig() + " " + currentCase.getCol() + " HEAT " + ((CaseTraversable) currentCase).getChaleur());
                    } else if (randomNumber > 190) {
                        currentTraversable.decrementeChaleur();
                        //System.out.println("Dec " + currentCase.getLig() + " " + currentCase.getCol() + " HEAT " + ((CaseTraversable) currentCase).getChaleur());
                    }
                }
            }
        }
    }
}
