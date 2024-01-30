import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.awt.desktop.ScreenSleepEvent;

public class FenetreJeu extends JPanel implements KeyListener{
    private Terrain terrain;
    private int tailleCase =35;
    private int hauteur, largeur;
    private JFrame frame;
    private int tailleFenetre = 9 * tailleCase;
    private String direction = "up";
    BufferedImage murImage, murImage_portrait, porteImg, keyImg, sortieImg, down_1, down_2,
            left_1, left_2, right_1, right_2, up_1, up_2;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;

        //setBackground(new Color(241, 241, 241));
        setPreferredSize(new Dimension(tailleFenetre, tailleFenetre));

        JFrame frame = new JFrame("Furfeux");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        try {
            murImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/mur.png")));
            murImage_portrait = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/mur_portrait.png")));
            porteImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/porte.png")));
            keyImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/key.png")));
            sortieImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/sortie.png")));
            down_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_down_1.png")));
            down_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_down_2.png")));
            left_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_left_1.png")));
            left_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_left_2.png")));
            right_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_right_1.png")));
            right_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_right_2.png")));
            up_1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_up_1.png")));
            up_2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Images/boy_up_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Methode PaintComponent
    // Entree: variable g de type Graphics utilisée pour dessiner sur des composants graphiques
    // Sortie: dessine uniquement les composants au alentour du joueur
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int JoueurLig = terrain.getJoueur().getC().getLig();
        int JoueurCol = terrain.getJoueur().getC().getCol();

        int ScreenMinLig= (JoueurLig * tailleCase) - (3 * tailleCase);
        int ScreenMinYCol= (JoueurCol * tailleCase) - (3 * tailleCase);

        int offsetMinLig = (hauteur * tailleCase) - ScreenMinLig;
        int offsetMinCol = (largeur * tailleCase) - ScreenMinYCol;

        for (int i = 0; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {

                Case CaseJoueur = terrain.getJoueur().getC();
                Case courante = terrain.getCase(i, j);

                // distanceSquared renvoie la distance entre le bloc et la position du joueur
                int distanceSquared = (JoueurLig - i) * (JoueurLig - i) + (JoueurCol - j) * (JoueurCol - j);

                //Si on est dans une case de distance de maximum 10 autour du joueur on dessine cette case
                if (distanceSquared <= 10) {
                    //Dans le cas ou ma case est la case sur lequel se trouve le joueur
                    if (courante == CaseJoueur) {
                        int chaleur = ((CaseTraversable) courante).getChaleur();
                        g.setColor(getColorForHeat(chaleur));
                        g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                        g.drawImage(getBoy(), offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase, null);

                    //Dans le cas ou ma case est un mur
                    } else if (courante instanceof Mur) {
                        //Si on est sur le bord du manoir on dessine le mur verticalement(image murImage_portrait)
                        if (murImage != null) {
                            if (j == 0 || j == largeur - 1) {
                                g.setColor(new Color(255, 255, 255));
                                g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                                g.drawImage(murImage_portrait, offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase, null);
                            //Sinon on le met horizontalement(image murImage)
                            } else {
                                g.setColor(new Color(255, 255, 255));
                                g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                                g.drawImage(murImage, offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase, null);
                            }
                        }
                    //Dans le cas ou ma case est une porte
                    } else if (courante instanceof Porte) {
                        //Ici on devait traiter les deux cas ou la porte est ouverte est fermer mais on a un background blanc derriere l'image de la porte donc
                        if (((Porte) courante).estFerme()) {
                            g.setColor(new Color(255, 255, 255));
                            g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                            g.drawImage(porteImg, offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase, null);
                        }
                    } else if (courante instanceof Sortie) {
                        g.setColor(new Color(255, 255, 255));
                        g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                        g.drawImage(sortieImg, offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase, null);
                        //g.setColor(Color.BLUE);
                        //g.fillRect(offsetMinCol+j * tailleCase - tailleCase *23, offsetMinLig+i * tailleCase -tailleCase *11, tailleCase, tailleCase);
                    } else if (courante instanceof Hall) {
                        if (((Hall) courante).contientCle()) {
                            g.setColor(new Color(255, 255, 255));
                            g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                            g.drawImage(keyImg, (offsetMinCol + j * tailleCase - tailleCase * 23), offsetMinLig + i * tailleCase - tailleCase * 11, 20, 20, null);                        } else {

                            int chaleur = ((CaseTraversable) courante).getChaleur();

                            g.setColor(getColorForHeat(chaleur));
                            g.fillRect(offsetMinCol + j * tailleCase - tailleCase * 23, offsetMinLig + i * tailleCase - tailleCase * 11, tailleCase, tailleCase);
                        }
                    }
                }
            }
        }
    }

    /**
     * @param heat un entier qui represente une certaine chaleur
    * Renvoie la couleur correspondant a la chaleur d'une case
    **/
    private Color getColorForHeat(int heat) {
        switch (heat) {
            case 1:
                return new Color(255, 204, 204);
            case 2:
                return new Color(253, 184, 184);
            case 3:
                return new Color(255, 164, 164);
            case 4:
                return new Color(255, 144, 144);
            case 5:
                return new Color(255, 124, 124);
            case 6:
                return new Color(255, 104, 104);
            case 7:
                return new Color(255, 84, 84);
            case 8:
                return new Color(255, 64, 64);
            case 9:
                return new Color(255, 44, 44);
            case 10:
                return new Color(255, 4, 4);
            default:
                return new Color(255, 255, 255);
        }
    }

    /**Les methodes KeyTyped et KeyReleased ne sont pas utilisées mais elles ont été ajouter car il est obligatoire d'inclure
     * toute les methodes de Keylistener
    **/
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * @return la direction du joueur
     */
    public String getDirection() {
        return direction;
    }

    /** @param direction la direction en String qu'on veut entrer
     * Change la direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**Parametre: La touche de clavier (flèche de haut,de bas, de droite et de gauche)
    *Bouge la case selon la flèche touché au clavier et définis la direction du Joueur selon la flèche rentré
    **/
     @Override
    public void keyPressed(KeyEvent e){
        int x = terrain.getJoueur().getC().getLig();
        int y = terrain.getJoueur().getC().getCol();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                setDirection("up");
                terrain.getJoueur().bouge(terrain.getCase(x - 1, y));
                break;
            case KeyEvent.VK_DOWN:
                setDirection("down");
                terrain.getJoueur().bouge(terrain.getCase(x + 1, y));
                break;
            case KeyEvent.VK_RIGHT:
                setDirection("right");
                terrain.getJoueur().bouge(terrain.getCase(x, y + 1));
                break;
            case KeyEvent.VK_LEFT:
                setDirection("left");
                terrain.getJoueur().bouge(terrain.getCase(x, y - 1));
                break;
        }
        this.repaint();
    }

    /**Cette methode anime les mouvements du Joueur en changeant le numéro SpriteNum qui est mis soi a 1 et soit a 2.
    *@return l'image qui match la direction du joueur
     **/
    public BufferedImage getBoy() {
        BufferedImage img = null;
        String dir = getDirection();
        int SN = terrain.getJoueur().getSpriteNum();

        if (dir != null) {
            switch (dir) {
                case "up":
                    if (SN == 1) {
                        img = up_1;
                    } else if (SN == 2) {
                        img = up_2;
                    }
                    break;
                case "down":
                    if (SN == 1) {
                        img = down_1;
                    } else if (SN == 2) {
                        img = down_2;
                    }
                    break;
                case "left":
                    if (SN == 1) {
                        img = left_1;
                    } else if (SN == 2) {
                        img = left_2;
                    }
                    break;
                case "right":
                    if (SN == 1) {
                        img = right_1;
                    } else if (SN == 2) {
                        img = right_2;
                    }
                    break;
            }
        }
        return img;
    }

    /** ecrannFinalWin prend le score n du joueur qui représente ca résistance/
    *Représente l'écran final après fin du jeu quand le joueur a gagner ca partie avec des images publiques prises d'internet.
    **/
    public void ecranFinalWin(int n) {
        frame.remove(this);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        finalPanel.setBackground(Color.LIGHT_GRAY);

        //Le texte du Score affiché sur l'écran représentant la résitance restante du joueur a la fin de ca partie.
        JLabel score = new JLabel("Score " + n);
        score.setFont(new Font("Ink Free", Font.BOLD, 40));
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setPreferredSize(new Dimension(200, 50));
        score.setForeground(Color.BLUE);
        finalPanel.add(score, BorderLayout.NORTH);

        //Image decorative qui represente un succes pour le joueur
        ImageIcon icon = new ImageIcon("src/Images/hurray.gif");
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        finalPanel.add(imageLabel, BorderLayout.CENTER);

        finalPanel.setPreferredSize(new Dimension(tailleFenetre, tailleFenetre));
        frame.getContentPane().add(finalPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.repaint();
    }

    /** ecrannFinalLost prend le score n du joueur qui représente ca resistance.
    * représente l'écran final après fin du jeu quand le joueur a perdu la partie avec des images publiques prises d'internet.
    **/
     public void ecranFinalLost(int n) {
        frame.remove(this);

        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new BorderLayout());
        finalPanel.setBackground(Color.LIGHT_GRAY);

         JLabel game_over = new JLabel("GAME OVER !");
         game_over.setFont(new Font("Ink Free", Font.BOLD, 30));
         game_over.setHorizontalAlignment(SwingConstants.HORIZONTAL);
         game_over.setPreferredSize(new Dimension(200, 50));
         game_over.setForeground(Color.RED);
         finalPanel.add(game_over, BorderLayout.NORTH);


         finalPanel.setPreferredSize(new Dimension(tailleFenetre, tailleFenetre));
        frame.getContentPane().add(finalPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.repaint();
    }
}
