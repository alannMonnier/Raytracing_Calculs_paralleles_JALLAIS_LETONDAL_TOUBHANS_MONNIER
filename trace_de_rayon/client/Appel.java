package trace_de_rayon.client;

import trace_de_rayon.Service.ServiceNoeud;
import trace_de_rayon.raytracer.Disp;
import trace_de_rayon.raytracer.Scene;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Appel {
    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry(args[0], 1099);
            ServiceNoeud serviceNoeud = (ServiceNoeud) registry.lookup("raytracer");
            Client client = new Client(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
            Disp disp = new Disp("Raytracer", client.getLargeur(), client.getLargeur());
            Scene scene = new Scene(client.getFichier(), client.getLargeur(), client.getHauteur());
            serviceNoeud.realiserCalcul(client, disp, scene);
            System.out.println("succes");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("echec");
        }
    }

}
