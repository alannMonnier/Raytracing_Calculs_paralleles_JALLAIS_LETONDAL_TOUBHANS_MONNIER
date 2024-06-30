package trace_de_rayon.client;

import java.io.Serializable;

public class Client implements Serializable {

    private int hauteur;
    private int largeur;
    String cheminFichier;

    public Client(int hauteur, int largeur, String fichier){
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.cheminFichier = fichier;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur(){
        return largeur;
    }

    public String getFichier(){
        return cheminFichier;
    }
}
