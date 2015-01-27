package puzzle;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * <p>Interfaccia che rappresenta gruppi di tasselli, dei quali è possibile:</p>
 * <ul>
 *     <li>riempirli</li>
 *     <li>contare quanti pezzi contengono su un certo bordo</li>
 *     <li>prelevare un certo pezzo conoscendone l'id</li>
 *     <li>prelevare un certo pezzo sul bordo conoscendone un vicino</li>
 *     <li>verificare se contiene almeno un pezzo</li>
 * </ul>
 * @author svalle
 */
public interface Gruppo extends Remote {
	public int conta(Dir side) throws RemoteException;
	public PuzzleItem getPiece(String id) throws RemoteException;
	public PuzzleItem getEdgePiece(Dir edge, Dir side, String ref) throws RemoteException;
	public boolean isEmpty() throws RemoteException;
	public void fill(ArrayList<String> rows) throws RemoteException;
}
