package trace_de_rayon.Service;

import java.util.ArrayList;
import trace_de_rayon.client.Client;
import trace_de_rayon.raytracer.Disp;
import trace_de_rayon.raytracer.Scene;

public class ServeurNoeud implements ServiceNoeud{

    private ArrayList<Noeud> noeuds;
    GestionCalcul gestionCalcul;

    public ServeurNoeud(GestionCalcul gestionCalcul){
        this.noeuds = new ArrayList<>();
        this.gestionCalcul = gestionCalcul;
    }


    public Noeud getCoordsNoeud(int indice){
        return this.noeuds.get(indice);
    }

    public ArrayList<Noeud> getNoeuds(){
        return this.noeuds;
    }
    
    public void enregistrerNoeud(Noeud noeud){
        try {
            this.noeuds.add(noeud);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Echec de l'ajout du noaud au serveur");
        }
    }

    public void realiserCalcul(Client client, Disp disp, Scene scene){
        gestionCalcul.repartirCalcul(this, client.getLargeur(), client.getHauteur(), scene);
    }
}