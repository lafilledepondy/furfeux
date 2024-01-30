import java.util.ArrayList;

public class CaseTraversable extends Case{
    /* ATTRIBUTS */
    private int chaleur;
    protected boolean contientEntite;


    /* CONSTRUCTEUR */
    /**
     * Constructeur par défaut de la classe CaseTraversable.
     *
     * @param lig La position en ligne de la case.
     * @param col La position en colonne de la case.
     */
    public CaseTraversable(int lig, int col){
        super(lig,col);
        this.contientEntite = false;
        this.chaleur = 0;
    }

    /**
     * Constructeur de la classe CaseTraversable avec une valeur de chaleur initiale.
     *
     * @param lig      La position en ligne de la case.
     * @param col      La position en colonne de la case.
     * @param chaleur  La valeur initiale de chaleur de la case.
     */
    public CaseTraversable(int lig, int col, int chaleur){
        super(lig,col);
        this.contientEntite = false;
        if (chaleur >= 0 && chaleur <= 10) {
            this.chaleur = chaleur;
        }
    }

    /**
     * Constructeur de la classe CaseTraversable avec une indication de la présence d'une entité.
     *
     * @param lig              La position en ligne de la case.
     * @param col              La position en colonne de la case.
     * @param contientEntite   Un booléen indiquant si la case contient une entité.
     */
    public CaseTraversable(int lig,int col, boolean contientEntite){
        super(lig,col);
        this.chaleur = 0;
        this.contientEntite = contientEntite;
    }


    /* METHODES */

    /**
     * Vérifie si la case est traversable, c'est-à-dire si elle ne contient pas d'entité.
     * @return true si la case est traversable, false sinon.
     */
    @Override
    public boolean estTraversable(){
        return !contientEntite;
    }

    /**
     * Obtient la valeur de chaleur de la case.
     * @return La valeur de chaleur de la case.
     */
    public int getChaleur(){
        return this.chaleur;
    }

    /**
     * Modifie la présence d'une entité dans la case.
     * @param contientEntite true si la case contient une entité, false sinon.
     */
    public void setContientEntite(boolean contientEntite) {
        this.contientEntite = contientEntite;
    }

    /**
     * Incrémente la valeur de chaleur de la case si elle n'est pas déjà au maximum.
     */
    public void incrementeChaleur(){
        if (this.chaleur < 9) {
            this.chaleur++;
        }
    }

    /**
     * Décrémente la valeur de chaleur de la case si elle n'est pas déjà au minimum.
     */
    public void decrementeChaleur(){
        if (this.chaleur > 0) {
            this.chaleur--;
        }
    }

    /**
     * Calcule la somme totale de chaleur en considérant les cases traversables voisines.
     * @param traversables La liste des cases traversables voisines.
     * @return La somme totale de chaleur.
     */
    public int calculateTotalHeat(ArrayList<CaseTraversable> traversables) {
        int totalHeat = this.chaleur;
        for (CaseTraversable neighbor : traversables) {
            totalHeat += neighbor.getChaleur();
        }
        return totalHeat;
    }

}
