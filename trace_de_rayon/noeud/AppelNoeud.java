package trace_de_rayon.noeud;

import trace_de_rayon.Service.Noeud;
import trace_de_rayon.Service.NoeudInterface;
import trace_de_rayon.Service.ServiceNoeud;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class AppelNoeud {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(args[0], 1099);

            for(String service : registry.list()){
                System.out.println("Service : " + service);
            }

            ServiceNoeud serviceNoeud = (ServiceNoeud) registry.lookup("raytracer");
            NoeudInterface nd = new Noeud(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            ServiceNoeud snd = (ServiceNoeud) UnicastRemoteObject.exportObject(nd, 0);
            //Il faut exportObject nd pour en faire un service, avant de le passer à serviceNoeud.enregistrerNoeud ?
            serviceNoeud.enregistrerNoeud((Noeud) snd);
            System.out.println(nd + " Est ajouté à la liste de noeud");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" Echec de l'ajout du noeud");
        }
    }
}
