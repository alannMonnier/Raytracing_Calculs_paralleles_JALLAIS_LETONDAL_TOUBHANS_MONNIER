package trace_de_rayon.Service;

import trace_de_rayon.raytracer.Disp;
import trace_de_rayon.raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NoeudInterface extends Remote {

    /**
     * Calcul un morceau d'une scène donné
     * @throws RemoteException
     */
    public void calculMorceauScene(Disp disp, Scene scene, int[] coords) throws RemoteException;
}
