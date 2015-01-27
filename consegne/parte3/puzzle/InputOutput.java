package puzzle;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p>Classe di utilità per le operazioni di input e output.</p>
 * <p>Richiede Java 7.</p>
 * @author svalle
 */
public class InputOutput { //visibilità package
	private static Charset charset = StandardCharsets.UTF_8;	//charset dei file letti/scritti

	/**
	 * <p>Metodo che restituisce un ArrayList contenente le righe del file di input.</p>
	 * @param path	path dove è situato il file di input
	 * @return	ArrayList di righe che compongono il file di input
	 */
	public static ArrayList<String> readContent(Path path){
		ArrayList<String> content = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(path,
			charset)) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.add(line);
			}
			return content;
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}

	/**
	 * <p>Metodo che scrive su un file un certo contenuto di tipo stringa
	 * codificato in UTF-8.</p>
	 * @param path	path dove scrivere il contenuto
	 * @param content	contenuto da scrivere sul file di output
	 */
	public static void writeContent(Path path, String content) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
		writer.write(content);
		} catch (IOException e) {
		System.err.println(e);
		}
	}
}
