import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author svalle
 */
public class InputOutput { //visibilità package
	private static Charset charset = StandardCharsets.UTF_8;
	
	public static ArrayList<String> readContent(Path path){
		ArrayList<String> content = new ArrayList<String>();
		try (BufferedReader reader = Files.newBufferedReader(path,
			charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.add(line);
			}
			return content;
		} catch (IOException e) {
			System.err.println(e);
		}
		return null;
	}
	
	public static void writeContent(Path path, String content) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
		writer.write(content);
		} catch (IOException e) {
		System.err.println(e);
		}
	}
}
