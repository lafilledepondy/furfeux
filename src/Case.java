import java.util.ArrayList;

public abstract class Case {
    private final int lig, col;
    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe abstraite case.
     * @param l La position en ligne de la case.
     * @param c La position en colonne de la case.
     */
    public Case(int l, int c) {
        this.lig = l;
        this.col = c;
    }
    /* METHODES */
    /**
     * @return la ligne de la case
     */
    public int getLig() {
        return lig;
    }
    /**
     * @return la colonne de la case
     */
    public int getCol() {
        return col;
    }
    /**
     * Vérifie si la case est traversable, c'est-à-dire si elle ne contient pas d'entité.
     * @return true si la case est traversable, false sinon.
     */
    public abstract boolean estTraversable();
}
