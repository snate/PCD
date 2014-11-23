import java.nio.file.Path;

/**
 * @author svalle
 */
public interface Gruppo {
	public void fill(Path path);
	public int conta(Dir side);
	public PuzzleItem getPiece(String id);
	public PuzzleItem getEdgePiece(Dir edge, Dir side, String ref);
	public boolean isEmpty();
}
