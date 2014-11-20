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
class InputOutput { //visibilità package
	private static Charset charset = StandardCharsets.UTF_8;
	
	public static String readContent(Path path){
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(path,
			charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line + "\n ");
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	return content.toString();
	}
	
	public static void writeContent(Path path, String content) {
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
		writer.write(content);
		} catch (IOException e) {
		System.err.println(e);
		}
	}
}
