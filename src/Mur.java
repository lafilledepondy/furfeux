public class Mur extends Case{
    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe Mur.
     *
     * @param l Position du mur en ligne.
     * @param c Position du mur en colonne.
     */
    public Mur(int l, int c){
        super(l,c);
    }


    /* METHODES */
    /**
     * Indique si le mur est traversable.
     *
     * @return toujours false, car un mur n'est pas traversable.
     */
    public boolean estTraversable() {
        return false;
    }
}
