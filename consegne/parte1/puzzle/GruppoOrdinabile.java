package puzzle;
import java.nio.file.Path;
/**
 * @author svalle
 *
 */
public interface GruppoOrdinabile extends Gruppo {
	public void sort();
	public void write(Path path);
}
