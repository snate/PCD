package puzzle;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p>Classe che fornisce metodi di utilità per l'aggiunta di una voce al registro RMI e la ricerca di una voce in
 * questo.</p>
 * @author svalle
 */
public class RMIBinder {

    public static Object richiedi(String query) throws Exception {
        return richiedi(query,null);
    }

    public static Object richiedi(String query,Remote obj) throws Exception {
        boolean server = true;
        if (obj == null)
            server = false;
        try {
            if (server) {
                Naming.rebind(query, obj);
                return null;
            }
            else
                return Naming.lookup(query);
        } catch (NotBoundException ne) {
            System.out.println(obj);
            System.out.println("Nome assente nel registro RMI");
            throw new Exception();
        } catch (RemoteException re) {
            System.out.println("Errore durante la comunicazione con il registro RMI");
            System.out.println(re.getMessage());
            throw new Exception();
        } catch (MalformedURLException me) {
            System.out.println("URL non corretto");
            throw new Exception();
        }
    }
}
