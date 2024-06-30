package trace_de_rayon.Service;

import trace_de_rayon.raytracer.Disp;
import trace_de_rayon.raytracer.Image;
import trace_de_rayon.raytracer.Scene;

import java.rmi.RemoteException;

public class Noeud implements NoeudInterface, Runnable{

    private int hauteur;
    private int largeur;
    private Disp disp;
    private Scene scene;
    private int[] coords;
    private boolean isComputing;

    public Noeud(int x, int y){
        this.hauteur = x;
        this.largeur = y;
    }

    public void calculMorceauScene(Disp disp, Scene scene, int[] coords) throws RemoteException {
        this.isComputing = true;
        // Créer l'image avec aux coordonnées x, y de taille largueur X hauteur
        Image image = scene.compute(coords[0], coords[1], coords[2], coords[3]);
        // Affiche l'image
        disp.setImage(image, coords[0], coords[1]);
        this.isComputing = false;
    }

    @Override
    public void run(){
        try {
            calculMorceauScene(this.disp, this.scene, this.coords);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    //La classe doit pouvoir :
    //Fournir une méthode permettant de calculer le Morceau d'une scène
    public int getX() {
        return hauteur;
    }

    public int getY() {
        return largeur;
    }
    public void setDisp(Disp disp){
        this.disp = disp;
    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public void setCoords(int[] coords){
        this.coords = coords;
    }

    public boolean isComputing(){
        return this.isComputing;
    }
}
