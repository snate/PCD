import java.util.Iterator;
import java.nio.file.Path;

/**
 * @author svalle
 */
public interface Gruppo {
	public static final String[] DIR = {"n", "e", "w", "s"};
	public void fill(Path path);
	public int dim(String side);
	public boolean isEmpty();
	public Iterator<PuzzleItem> iterator();
}
