import java.nio.file.Path;
import java.nio.file.Paths;/**
 * @author svalle
 */
public class PuzzleSolver {

	/**
	 * @param {string} stream - Path dei file di input e output
	 */
	public static void main(String[] args) {
		String pathIn = args[0];
		String pathOut = args[1];
		Path inputPath = Paths.get(pathIn);
		Path outputPath = Paths.get(pathOut);
		GruppoOrdinabile p = new Puzzle();
		p.fill(inputPath);
		p.sort();
		p.write(outputPath);
	}

}
