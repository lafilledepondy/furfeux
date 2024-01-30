import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.security.PublicKey;

public class Hall extends CaseTraversable{
    /* CONSTRUCTEUR */
    /**
     * Constructeur par défaut de la classe Hall.
     *
     * @param lig Position en ligne de la case.
     * @param col Position en colonne de la case.
     */
    public Hall(int lig, int col) {
        super(lig, col);
    }

    /**
     * Constructeur de la classe Hall avec indication de la chaleur initiale.     * @param lig      La position en ligne de la case.
     * @param col      La position en colonne de la case.
     * @param chaleur  La valeur initiale de chaleur de la case.
     */
    public Hall(int lig, int col, int chaleur){
        super(lig, col, chaleur);
    }

    /**
     * Constructeur de la classe CaseTraversable avec une indication de la présence d'une entité.
     *
     * @param lig              La position en ligne de la case.
     * @param col              La position en colonne de la case.
     * @param contientEntite   Un booléen indiquant si la case contient une entité.
     */
    public Hall(int lig, int col, boolean contientEntite) {
        super(lig, col, contientEntite);
    }


    /* METHODES */
    /**
     * Permet au joueur de pénétrer dans la case Hall.
     *
     * @param j Joueur qui entre dans la case.
     */
    public void entre(Joueur j){
        this.contientEntite = true;
        if(contientCle()){
            System.out.println("La case initiale du joueur contient une cle.");
            System.out.println("on prend la cle");
        }
        enleveCle();
    }

    /**
     * Vérifie si la case Hall contient une clé.
     *
     * @return true si la case contient une clé, false sinon.
     */
    public boolean contientCle(){
        return !estTraversable();
    }

    /**
     * Enlève la clé de la case Hall.
     */
    public void enleveCle(){
        setContientEntite(false);
    }
}
