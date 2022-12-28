/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package demineur_cordier_elziere_dompietrini;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Nina
 */
public class PlateauDeJeu extends javax.swing.JPanel {

    /*initialisation des constantes*/
    private final int NB_IMAGE = 13;
    /*on a 13 images comprenant le drapeau, mines, numéros ...*/
    private final int TAILLE_CELL = 15;
    /*images de 15 pixels*/
    private int NB_VIE = 3; /*le joueur a 3 vies*/

    private final int CELL_COUV = 10;
    /*état de la case initiale : couverte*/
    private final int CELL_MINE = 9;
    /*état de la case s'il y a une mine*/
    private final int CELL_MINE_COUV = CELL_MINE + CELL_COUV;
    /*si la cellule est minée et couverte*/
    private final int CELL_VIDE = 0;
    /*état de la case sans bombe*/
    private final int CELL_MARQUAGE = 10;
    /*si on retire le drapeau qu'on avait placé : simple case couverte*/
    private final int CELL_MINE_DRAPEAU = CELL_MINE_COUV + CELL_MARQUAGE;

    private final int IMG_MINE = 9;
    private final int IMG_COUVERTE = 10;
    private final int IMG_DRAPEAU = 11;
    private final int IMG_MAUVAIS_PLACEMENT = 12;
    /*si le joueur met un drapeau là où il n'y a pas de bombe, cette image apparaitra quand il aura perdu*/

    private final int NB_MINES = 50;
    /*nombre de mines initialisé à 50*/
    private final int NB_LIG = 16;
    /*16 lignes*/
    private final int NB_COL = 30;
    /*30 colonnes*/

    private final int LARGEUR = NB_COL * TAILLE_CELL + 1;
    /*pour que le Panel s'adapte*/
    private final int HAUTEUR = NB_LIG * TAILLE_CELL + 1;


    /*ajout de propriétés*/
    private int[][] tableau = new int[NB_COL][NB_LIG];
    /*initialisation du tableau 2D à 30 colonnes et 16 lignes soit x, y*/
    private boolean en_jeu;
    /*permettra de voir si le joueur a gagné ou non*/
    private int mines_restantes;
    /*nombre de mines non découvertes*/

    private int nb_cellules;
    /*nombre de cellules totales*/
    private JLabel message;
    private Image[] img;

    /**
     * Creates new form PlateauDeJeu
     */
    public PlateauDeJeu() {
        this.message = new JLabel();
        initComponents();
        initialisation();
    }

    public void setmessage(JLabel message){ /*on crée un setter pour pouvoir ajouter les messages au design*/
        this.message = message;
    }
    
    private void initialisation() {

        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

        img = new Image[NB_IMAGE];

        for (int i = 0; i < NB_IMAGE; i++) {

            var chemin = "src/images/" + i + ".png";
            img[i] = (new ImageIcon(chemin)).getImage();
        }
        //addMouseListener(new gestion_clic());
        //partie();
        addMouseListener(new gestion_clic());
        //partie();
    }

    /*initialisation de la grille avec positionnement des mines*/

    public void partie() {
        /*on fait en 2D*/
        NB_VIE = 3;
        int cellule;

        var random = new Random();
        en_jeu = true;
        /*tant que le joueur n'a pas gagné*/
        mines_restantes = NB_MINES;

        for (int x = 0; x < NB_COL; x++) {
            /*on parcourt les colonnes*/
            for (int y = 0; y < NB_LIG; y++) {
                /*on parcourt les lignes*/
                tableau[x][y] = CELL_COUV;
            }
        }
        
        message.setText("Mines restantes : " + Integer.toString(mines_restantes) + "   " + "Vies restantes : " + NB_VIE);
        
        int i = 0;

        while (i < NB_MINES) {

            int x = random.nextInt(NB_COL);
            int y = random.nextInt(NB_LIG);

            if (tableau[x][y] != CELL_MINE_COUV) {
                /*si on a pas de mine couverte et qu'on est dans la grille*/

                tableau[x][y] = CELL_MINE_COUV;
                i++;

                if (x > 0) {
                    if (y > 0) {
                        /*car traitement inutile si on est déjà tout à gauche*/
                        if (tableau[x - 1][y - 1] != CELL_MINE_COUV) {
                            tableau[x - 1][y - 1] += 1;
                            /*on affecte la valeur sur la diagonale : en haut à gauche*/
                        }
                    }

                    if (tableau[x - 1][y] != CELL_MINE_COUV) {
                        tableau[x - 1][y] += 1;
                        /*a gauche*/
                    }

                    /*diagonale : en bas à gauche*/
                    if (y+1 < NB_LIG) {
                        /*on vérifie qu'on ne sort pas du tableau*/
                        if (tableau[x - 1][y + 1] != CELL_MINE_COUV) {
                            tableau[x - 1][y + 1] += 1;
                        }
                    }
                }

                /*au dessus*/
                if (y > 0) {
                    if (tableau[x][y - 1] != CELL_MINE_COUV) {
                        tableau[x][y - 1] += 1;
                    }
                }

                /*en dessous car on ajoute 30 ce qui nous place à la ligne du dessous car une dimension*/
                if (y+1 < NB_LIG) {
                    if (tableau[x][y + 1] != CELL_MINE_COUV) {
                        tableau[x][y + 1] += 1;
                    }
                }

                if (x+1 < NB_COL) {
                    /*diagonale : en haut à droite*/
                    if (y > 0) {
                        if (tableau[x + 1][y - 1] != CELL_MINE_COUV) {
                            tableau[x + 1][y - 1] += 1;
                        }
                    }
                    /*diagonale : en bas à droite*/
                    if (y+1 < NB_LIG) {
                        if (tableau[x + 1][y + 1] != CELL_MINE_COUV) {
                            tableau[x + 1][y + 1] += 1;
                        }
                    }
                    /*à droite*/
                    if (tableau[x + 1][y] != CELL_MINE_COUV) {
                        tableau[x + 1][y] += 1;
                    }
                }
            }
        }
    }
    
    private void decouvrir_cases_non_minees(int x, int y) {
        /*permettra de découvrir les cases adjacentes non minées*/

        if (x > 0) {
            /*en haut à gauche*/
            if (y > 0) {
                if (tableau[x-1][y-1] > CELL_MINE) {
                    tableau[x-1][y-1] -= CELL_COUV;
                    /*découvre la case*/
                    if (tableau[x-1][y-1] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x-1, y-1);
                        /*on répète le traitement pour la case qu'on vient de découvrir*/
                    }
                }
            }

            /*à gauche*/
                if (tableau[x-1][y] > CELL_MINE) {
                    tableau[x-1][y] -= CELL_COUV;
                    if (tableau[x-1][y] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x-1, y);
                    }
                }
            
            /*en bas à gauche*/
            if (y+1 < NB_LIG) {
                if (tableau[x-1][y+1] > CELL_MINE) {
                    tableau[x-1][y+1] -= CELL_COUV;
                    if (tableau[x-1][y+1] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x-1, y+1);
                    }
                }
            }
        }

        /*en haut*/
        if (y > 0) {
            if (tableau[x][y-1] > CELL_MINE) {
                tableau[x][y-1] -= CELL_COUV;
                if (tableau[x][y-1] == CELL_VIDE) {
                    decouvrir_cases_non_minees(x, y-1);
                }
            }
        }

        /*en bas*/
        if (y+1 < NB_LIG) {
            if (tableau[x][y+1] > CELL_MINE) {
                tableau[x][y+1] -= CELL_COUV;
                if (tableau[x][y+1] == CELL_VIDE) {
                    decouvrir_cases_non_minees(x, y+1);
                }
            }
        }

        if (x+1 < NB_COL) {
            /*en haut à droite*/
            if (y > 0) {
                if (tableau[x+1][y-1] > CELL_MINE) {
                    tableau[x+1][y-1] -= CELL_COUV;
                    if (tableau[x+1][y-1] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x+1, y-1);
                    }
                }
            }

            /*en bas à droite*/
            if (y + 1 < NB_LIG) {
                if (tableau[x+1][y+1] > CELL_MINE) {
                    tableau[x+1][y+1] -= CELL_COUV;
                    if (tableau[x+1][y+1] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x+1,y+1);
                    }
                }
            }

            /*à droite*/
                if (tableau[x+1][y] > CELL_MINE) {
                    tableau[x+1][y] -= CELL_COUV;
                    if (tableau[x+1][y] == CELL_VIDE) {
                        decouvrir_cases_non_minees(x+1, y);
                    }
                }
            
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        int nb_cell_couverte = 0;

        for (int x = 0; x < NB_COL; x++) {

            for (int y = 0; y < NB_LIG; y++) {

                int cell = tableau[x][y];
               
                if (en_jeu && cell == CELL_MINE) {
                    if (NB_VIE == 0){
                        en_jeu = false;
                    } 
                }

                if (!en_jeu) {

                    if (cell == CELL_MINE_COUV) {
                        cell = IMG_MINE;
                    } else if (cell == CELL_MINE_DRAPEAU) {
                        cell = IMG_DRAPEAU;
                    } else if (cell > CELL_MINE_COUV) {
                        cell = IMG_MAUVAIS_PLACEMENT;
                    } else if (cell > CELL_MINE) {
                        cell = IMG_COUVERTE;
                    }

                } else {

                    if (cell > CELL_MINE_COUV) {
                        cell = IMG_DRAPEAU;
                    } else if (cell > CELL_MINE) {
                        cell = IMG_COUVERTE;
                        nb_cell_couverte++;
                    }
                }

                g.drawImage(img[cell], (x * TAILLE_CELL),
                        (y * TAILLE_CELL), this);
            }
        }

        if (nb_cell_couverte == 0 && en_jeu) {
            /*car on gagne que si tout a été découvert*/

            en_jeu = false;
            message.setText("Vous avez gagné");

        } else if (!en_jeu) {
            message.setText("Vous avez perdu");
        }
    }

    private class gestion_clic extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            /*e est l'évènement : clique gauche / clique droit*/

            int x = e.getX() / TAILLE_CELL; /*divise par la taille de la cellule pour savoir dans quelle case on est : en fonction des pixels*/
            int y = e.getY() / TAILLE_CELL;

            boolean rafraichir = false;
            /*pour rafraichir l'interface pour afficher les interactions*/

            if (!en_jeu) {
                /*si le joueur a perdu on rejoue*/

                //partie();
                partie();
                repaint();
            }

            if (x < NB_COL && y < NB_LIG) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (tableau[x][y] > CELL_MINE) {

                        rafraichir = true;

                        if (tableau[x][y] <= CELL_MINE_COUV) {

                            if (mines_restantes > 0) {
                                tableau[x][y] += CELL_MARQUAGE;
                                mines_restantes--;
                                String msg = "Mines restantes : " + Integer.toString(mines_restantes) + "   " + "Vies restantes : " + NB_VIE;
                                message.setText(msg);
                            } else {
                                message.setText("Tous les drapeaux ont été placés");
                            }
                        } else {

                            tableau[x][y] -= CELL_MARQUAGE;
                            mines_restantes++;
                            String msg = "Mines restantes : " + Integer.toString(mines_restantes) + "   " + "Vies restantes : " + NB_VIE;
                            message.setText(msg);
                        }
                    }

                } else {
                    message.setText("Mines restantes : " + Integer.toString(mines_restantes) + "   " + "Vies restantes : " + NB_VIE);

                    if (tableau[x][y] > CELL_MINE_COUV) {

                        return;
                    }

                    if ((tableau[x][y] > CELL_MINE) && (tableau[x][y] < CELL_MINE_DRAPEAU)) {

                        tableau[x][y] -= CELL_COUV;
                        rafraichir = true;

                        if (tableau[x][y] == CELL_MINE) {
                            NB_VIE -= 1; /*si on clique sur une mine le joueur perd une vie*/
                            message.setText("Mines restantes : " + Integer.toString(mines_restantes) + "   " + "Vies restantes : " + NB_VIE);
                            if (NB_VIE == 0){
                                en_jeu = false;
                            }
                        }

                        if (tableau[x][y] == CELL_VIDE) {
                            decouvrir_cases_non_minees(x, y);
                        }
                    }
                }

                if (rafraichir) {
                    repaint();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
