/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demineur_cordier_elziere_dompietrini;

/**
 *
 * @author Nina
 */
public class Joueur {
    
    public String nom;
    private int nb_kit ;
    private int point_vie;
    
    public Joueur(String nom_joueur){ /*constructeur de la classe*/
        nom = nom_joueur;
        nb_kit = 0;
        point_vie= 3;
    }
    public void avoirKit(){ /*méthode pour ajouter un kit de déminage*/
        nb_kit = nb_kit+1;
    }
    
    public void utiliserKit(){ /*méthode pour utiliser, enlever un kit de déminage*/
        nb_kit = nb_kit-1;
    }
           
    public int getPointVie(){ /*on crée un getter pour le nombre de point de vie*/
        return point_vie;
    }
    
    public int getKit(){ /*on crée un getter pour le nombre de kit*/
        return nb_kit;
    }
     
    public int défaite(){
        point_vie = point_vie-1;
        return point_vie;
    }
    
}
