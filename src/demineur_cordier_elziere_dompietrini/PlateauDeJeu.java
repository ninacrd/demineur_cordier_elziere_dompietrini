/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package demineur_cordier_elziere_dompietrini;

import java.awt.Image;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Nina
 */
public class PlateauDeJeu extends javax.swing.JPanel {

    /*initialisation des constantes*/
    
    private final int NUM_IMAGE = 13; /*on a 13 images comprenant le drapeau, mines, numéros ...*/
    private final int TAILLE_CELL = 15; /*images de 15 pixels*/
    
    private final int CELL_COUV = 10; /*état de la case initiale : couverte*/
    private final int CELL_MINE = 9; /*état de la case s'il y a une mine*/
    private final int CELL_MINE_COUV = CELL_MINE + CELL_COUV; /*si la cellule est minée et couverte*/
    private final int CELL_VIDE = 0; /*état de la case sans bombe*/
    private final int CELL_MARQUAGE = 10; /*si on retire le drapeau qu'on avait placé : simple case couverte*/
    private final int CELL_MINE_DRAPEAU = CELL_MINE_COUV + CELL_MARQUAGE;
    
    private final int IMG_MINE = 9;
    private final int IMG_COUVERTE = 10;
    private final int IMG_DRAPEAU = 11;
    private final int IMG_MAUVAIS_PLACEMENT = 12; /*si le joueur met un drapeau laà où il n'y a pas de bombe, cette image apparaitra quand il aura perdu*/


    private final int NB_MINES = 50; /*nombre de mines initialisé à 50*/
    private final int NB_LIG = 16; /*16 lignes*/
    private final int NB_COL = 30; /*30 colonnes*/
    
    private final int LARGEUR = NB_COL * TAILLE_CELL + 1; /*pour que le Panel s'adapte*/
    private final int HAUTEUR = NB_LIG * TAILLE_CELL + 1;


    /*ajout de propriétés*/
    private int[] tableau;
    private boolean fin_jeu; /*permettra de voir si le joueur a gagné ou non*/
    private int mines_restantes; /*nombre de mines non découvertes*/

    private int nb_cellules; /*nombre de cellules totales*/
    private final JLabel message;

    /**
     * Creates new form PlateauDeJeu
     */
    public PlateauDeJeu() {
        initComponents();
        initialisation();
    }

    /*initialisation de la grille avec positionnement des mines*/
    private void initialisation() {

        int cellule;

        var random = new Random();
        fin_jeu = false; /*tant que le joueur n'a pas gagné*/
        mines_restantes = NB_MINES;

        nb_cellules = NB_LIG * NB_COL; /*tableau en une dimension au départ : précisé dans le doc word*/
        tableau = new int[nb_cellules];

        for (int i = 0; i < nb_cellules; i++) {
            tableau[i] = CELL_COUV; 
        }

        message.setText(Integer.toString(mines_restantes));

        int i = 0;

        while (i < NB_MINES) {

            int position = random.nextInt(nb_cellules);

            if ((position < nb_cellules) && (tableau[position] != CELL_MINE_COUV)) { /*si on a pas de mine couverte et qu'on est dans la grille*/

                int colonne = position % NB_COL; /*permet de choisir la colonne courante*/
                tableau[position] = CELL_MINE_COUV;
                i++;

                if (colonne > 0) {
                    cellule = position - 1 - NB_COL; 
                    if (cellule >= 0) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1; /*on affecte la valeur sur la diagonale : en haut à gauche*/
                        }
                    }
                    cellule = position - 1;
                    if (cellule >= 0) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1; /*a gauche*/
                        }
                    }

                    cellule = position + NB_COL - 1; /*diagonale : en bas à gauche*/
                    if (cellule < nb_cellules) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1;
                        }
                    }
                }

                cellule = position - NB_COL; /*au dessus*/
                if (cellule >= 0) {
                    if (tableau[cellule] != CELL_MINE_COUV) {
                        tableau[cellule] += 1;
                    }
                }

                cellule = position + NB_COL; /*en dessous car on ajoute 30 ce qui nous place à la ligne du dessous car une dimension*/
                if (cellule < nb_cellules) {
                    if (tableau[cellule] != CELL_MINE_COUV) {
                        tableau[cellule] += 1;
                    }
                }

                if (colonne < (NB_COL - 1)) { /*diagonale : en haut à droite*/
                    cellule = position - NB_COL + 1;
                    if (cellule >= 0) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1;
                        }
                    }
                    cellule = position + NB_COL + 1; /*diagonale : en bas à droite*/
                    if (cellule < nb_cellules) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1;
                        }
                    }
                    cellule = position + 1; /*en bas*/
                    if (cellule < nb_cellules) {
                        if (tableau[cellule] != CELL_MINE_COUV) {
                            tableau[cellule] += 1;
                        }
                    }
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
