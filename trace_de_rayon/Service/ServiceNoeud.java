package trace_de_rayon.Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import trace_de_rayon.client.Client;
import trace_de_rayon.raytracer.Scene;
import trace_de_rayon.raytracer.Disp;

public interface ServiceNoeud extends Remote {
    public void enregistrerNoeud(Noeud noeud) throws RemoteException;
    public void realiserCalcul(Client client, Disp disp, Scene scene) throws RemoteException;
}
