public class Porte extends CaseTraversable{
    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe Porte.
     *
     * @param l Position de la porte en ligne.
     * @param c Position de la porte en colonne.
     * @param o Boolean indiquant si la porte est ouverte (true) ou fermée (false).
     */
    public Porte(int l, int c, boolean o ) {
        super(l, c, !o);
    }


    /* METHODES */
    /**
     * Indique si la porte est fermée.
     *
     * @return true si la porte est fermée, false si elle est ouverte.
     */
    public boolean estFerme() {
        return !estTraversable();
    }

    /**
     * Déverrouille la porte, la rendant traversable.
     */
    public void Devrouiller() {
        this.contientEntite = false;
    }

    /**
     * Ouvre la porte en utilisant une clé du joueur spécifié.
     *
     * @param j Joueur qui tente d'ouvrir la porte.
     */
    public void Ouvrir(Joueur j){
        if(j.NbCle() > 0){
            Devrouiller();
            j.DecrementeCle();
            System.out.println("Porte ouverte!");
        }
    }
}
