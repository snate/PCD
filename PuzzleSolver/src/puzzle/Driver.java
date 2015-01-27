package puzzle;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author svalle
 */
public class Driver {
    public static void main(String[] args) {
		String pathIn = args[0]; 					//primo argomento passato alla consolle
		String pathOut = args[1];					//secondo argomento passato alla consolle
		Path inputPath = Paths.get(pathIn);
		Path outputPath = Paths.get(pathOut);
		Test test = new Test();
		inputPath = test.build(inputPath);
        if(args.length > 0)
            args[0] = inputPath.toString();
		PuzzleSolverServer.main(args);
		Path log = Paths.get("log");
		Date now = new Date();
		if(test.checkOut(outputPath)) {
			InputOutput.writeContent(log, "OK: " + now);
			System.out.println("OK");
		}
		else {
			InputOutput.writeContent(log, "ERRORE: " + now);
			System.out.println("ERRORE");
		}
    }
}
