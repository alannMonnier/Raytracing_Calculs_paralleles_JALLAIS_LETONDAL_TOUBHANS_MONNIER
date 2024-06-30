package trace_de_rayon.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerDistributeurNoeud {

    public static void main(String[] args) {
        try{
            GestionCalcul gestionCalcul = new GestionCalcul();
            Registry registry = LocateRegistry.createRegistry(1099);
            ServiceNoeud serviceNoeud = new ServeurNoeud(gestionCalcul);
            ServiceNoeud rd = (ServiceNoeud) UnicastRemoteObject.exportObject(serviceNoeud, 0);
            registry.rebind("raytracer", rd);
            System.out.println("Création du service réussi");
        }
        catch(RemoteException e){
            e.printStackTrace();
            System.out.println("Echec lors de la création du service");
        }
    }
}