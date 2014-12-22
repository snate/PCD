package puzzle;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        System.out.println(inputPath.toString());
		PuzzleSolver.main(args);
		if(test.checkOut(outputPath))
			System.out.println("OK");
		else
			System.out.println("ERRORE");
    }
}
