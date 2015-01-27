package puzzle;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *	<p>PuzzleSolverServer è la classe dove è situata la main procedure del
 *	risolutore di puzzle.</p>
 * @author svalle
 */
public class PuzzleSolverServer {

    /**
     * <p>Questa è la <em>main</em> procedure del PuzzleSolverServer.</p>
     * <p>Viene creato un oggetto di tipo GruppoOrdinabile che verrà poi usato dal Client per ordinare il puzzle.</p>
     * @param args	Nome del server (0)
     */
    public static void main(String[] args) {
        String nomeserver = "rmi://" + args[0] + "/Puzzle";
        GruppoOrdinabile p = null;
        try {
            p = new Puzzle();
        } catch (RemoteException re) {
            System.out.println("Problemi nella creazione del puzzle HERE");
            System.out.println(re.getMessage());
        }
        if(p!= null) {
            try { RMIBinder.richiedi(nomeserver,p); }
            catch(Exception e) {
                System.out.println("Registrazione RMI non riuscita");
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Tutto ok :)");
    }

}
