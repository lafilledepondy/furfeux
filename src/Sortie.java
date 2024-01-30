public class Sortie extends CaseTraversable {
    /* ATTRIBUTS */
    private int score;


    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe Sortie.
     *
     * @param lig   Ligne de la sortie sur le terrain.
     * @param col   Colonne de la sortie sur le terrain.
     * @param s     Le score attribué lors de la sortie.
     */
    public Sortie(int lig, int col, int s) {
        super(lig, col);
        this.score = s;
    }
}
