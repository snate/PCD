package puzzle;
import java.nio.file.Path;

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
public interface Gruppo {
	public void fill(Path path);
	public int conta(Dir side);
	public PuzzleItem getPiece(String id);
	public PuzzleItem getEdgePiece(Dir edge, Dir side, String ref);
	public boolean isEmpty();
}
