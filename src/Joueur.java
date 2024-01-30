public class Joueur {
    /* ATTRIBUTS */
    private CaseTraversable c;
    private int resistance;
    private int cles;
    public int spriteNum;


    /* CONSTRUCTEUR */
    /**
     * Constructeur de la classe Joueur.
     *
     * @param c La case initiale où se trouve le joueur.
     * @param r La résistance initiale du joueur.
     * @param k Le nombre initial de clés que le joueur possède.
     */
    public Joueur(CaseTraversable c, int r, int k) {
        this.c = c;
        this.resistance = r;
        this.cles = k;
        spriteNum = 1;

        if(((Hall)c).contientCle()){
            System.out.println("La case de départ contient une clé !");
            IncrementeCle();
            ((Hall)c).enleveCle();
        }
    }


    /* METHODES */
    /**
     * Incrémente le nombre de clés que le joueur possède.
     */
    public void IncrementeCle(){
        this.cles ++;
    }

    /**
     * Décrémente le nombre de clés que le joueur possède.
     */
    public void DecrementeCle(){
        this.cles --;
        System.out.println(this.cles + " cles restante!");
    }

    /**
     * Décrémente la résistance du joueur.
     *
     * @param r La valeur à déduire de la résistance.
     */
    public void DecrementeResistance(int r){
        this.resistance -= r;
        //System.out.println(resistance);
    }

    /**
     * Obtient la valeur actuelle de la résistance du joueur.
     *
     * @return La résistance actuelle du joueur.
     */
    public int getResistance(){
        return this.resistance;
    }

    /**
     * Obtient la case sur laquelle se trouve actuellement le joueur.
     *
     * @return La case actuelle du joueur.
     */
    public CaseTraversable getC(){
        return this.c;
    }

    /**
     * Obtient le nombre actuel de clés que le joueur possède.
     *
     * @return Le nombre actuel de clés du joueur.
     */
    public int NbCle(){
        return cles;
    }

    /**
     * Obtient le nombre actuel de sprite.
     *
     * @return Le nombre sprite.
     */
    public int getSpriteNum() {
        return spriteNum;
    }

    /**
     * Déplace le joueur vers une case cible.
     *
     * @param cible La case vers laquelle le joueur se déplace.
     */
    public void bouge(Case cible) {
        if(cible instanceof Sortie){
            //System.out.println("sortie");
            this.c = (CaseTraversable) cible;
        } else if(cible instanceof Hall){
            //System.out.println("hall");
            if(((Hall)cible).contientCle()){
                ((Hall)cible).enleveCle();
                IncrementeCle();
            }
            this.c = (CaseTraversable) cible;
        } else if (cible instanceof Porte) {
            //System.out.println("Porte");
            if(((Porte)cible).estFerme() && this.cles > 0) {
                ((Porte) cible).Ouvrir(this);
                this.c = (CaseTraversable) cible;
            } else if(!((Porte)cible).estFerme() ){
                this.c = (CaseTraversable) cible;
            }
        }

        if (spriteNum == 1){
            spriteNum = 2;
        } else if (spriteNum == 2){
            spriteNum = 1;
        }
    }
}
