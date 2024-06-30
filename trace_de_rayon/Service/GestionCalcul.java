package trace_de_rayon.Service;

import trace_de_rayon.raytracer.Disp;
import trace_de_rayon.raytracer.Scene;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ListIterator;

public class GestionCalcul extends Thread implements Serializable {

    private Scene scene;
    private Disp disp;


    public void repartirCalcul(ServeurNoeud serveurNoeud, int hauteur, int largeur, Scene scene) {
        this.scene = scene;
        ArrayList<Noeud> listeNoeuds = serveurNoeud.getNoeuds();

        int nbSection = listeNoeuds.size() * 4;
        int nbSectionsRestante = 0;

        // Calcul des points de départ de chaque section que l'on va calculer
        int largeurSection = nbSection / largeur;
        int hauteurSection = nbSection / hauteur;
        ArrayList<Coords> coords = new ArrayList<>();

        int caseX = 0;
        int caseY = 0;

        for (int x = 0; x < nbSection; x++) {
            for (int y = 0; y < nbSection; y++) {
                coords.add(new Coords(caseX, caseY));
                caseY += hauteurSection;
            }
            caseX += largeurSection;
            caseY = 0;
        }
            
        int indiceNoeud = 0;
        while (indiceNoeud < nbSection) {
            try{
                //On prend une machine qui ne calcule pas
                Noeud finalNoeudCourant = getNoeud(listeNoeuds);
                //On crée un thread avec la machine courante
                //On fait une copie de la variable indice (c'est intellij qui ma obligé à le faire)
                int finalIndiceNoeud = indiceNoeud;
                Thread thread = new Thread(() -> {
                    try {
                        //On lui donne la scene actuelle et la disp actuelle
                        //On lui attribue la section i (par les coords)
                        //On lance le calcul
                        finalNoeudCourant.calculMorceauScene(this.disp, this.scene, new int[]{coords.get(finalIndiceNoeud).getX(), coords.get(finalIndiceNoeud).getY(), largeurSection, hauteurSection});
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
                //On lance le thread
                thread.start();
                indiceNoeud++;
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
            while (nbSectionsRestante < nbSection) {
                ListIterator<Noeud> iter = (ListIterator<Noeud>) listeNoeuds.iterator();
                while (iter.hasNext()) {
                    iter.remove();
                    iter.
                }
                for (Noeud nd : listeNoeuds) {
                    try {
                        //On prend une machine
                        Noeud machineCourante = (Noeud) nd;
                        machineCourante.setDisp(this.disp);
                        machineCourante.setScene(this.scene);
                        //machineCourante.setCoords(coords.get(indiceNoeud));//Voir ligne 60 pour mettre le bon parametre
                        machineCourante.setCoords(new int[]{coords.get(indiceNoeud).getX(), coords.get(indiceNoeud).getY(), largeurSection, hauteurSection});
                        indiceNoeud++;
                        Thread thread = new Thread(machineCourante);
                        thread.start();
                        int[] coordsSect = new int[]{coords.get(indiceNoeud).getX(), coords.get(indiceNoeud).getY(), largeurSection, hauteurSection};
                        machineCourante.calculMorceauScene(disp, scene, coordsSect);
                        listeNoeuds.remove(nd);
                    }catch (RemoteException re){
                        re.printStackTrace();
                        // Donner le calcul à un noeud qui a fini le siens

                    }
                }
            }*/

                //On l'enlève de la liste, puis on lui lance le calcul sur un Thread.
                //while(thread.isAlive()) {
                //      System.out.println("Waiting...");
                //    }
                //Après cette boucle, le Thread est terminé. On remet donc la machine dans la liste afin de pouvoir lui redonner un calcul.
                //Pour lancer un calcul : Prendre un objet noeud, et grâce aux setters, mettre les paramètres Disp, Scene et coords.
                //Puis, appeler la méthode start(); de l'objet Noeud. ça va lancer un Thread et effectuer le calcul
                /**
                 try{
                 // Répartir chaque section aux différents clients

                 // Si aucune exception alors on considère que le calcul de la section est réussi
                 nbSectionsRestante++;
                 }
                 catch(RemoteException e){
                 System.out.println("Erreur de connexion");
                 // Donner le calcul à un noeud qui a fini le siens
                 }*/
        }
    //Méthode pour recevoir un noeud qui n'est pas en calcul
    private static Noeud getNoeud(ArrayList<Noeud> listeNoeuds) {
        Noeud noeudCourant = null;
        while (noeudCourant==null){
            for (Noeud nd : listeNoeuds){
                if (!nd.isComputing()){
                    noeudCourant = nd;
                }
            }
        }
        return noeudCourant;
    }
}
