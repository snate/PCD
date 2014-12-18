import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *	<p>PuzzleSolver è la classe dove è situata la main procedure del
 *	risolutore di puzzle.</p>
 * @author svalle
 */
public class PuzzleSolver {

	/**
	 * <p>Questa è la <em>main</em> procedure del programma
	 * PuzzleSolver.</p>
	 * <p>Inizialmente controlla che l'input sia ben-formato:
	 * se sì, mischia i tasselli in modo tale da garantire l'indipendenza
	 * del corretto funzionamento del programma dalla disposizione
	 * dei tasselli, altrimenti fa terminare il programma.</p>
	 * <p>Il programma procede alla creazione di un puzzle con i tasselli
	 * presenti nel file di input. Poi procede riordinandoli
	 * ed infine scrive nel file di output come richiesto da
	 * specifiche.</p>
	 * <p>In questa procedura è possibile eseguire un test per
	 * vedere se il programma funziona correttamente. Dato un
	 * testo scritto su una sola riga come input, il test
	 * crea un file di input chiamato "test" dove sono presenti
	 * i tasselli del puzzle. Viene eseguito il programma come indicato
	 * sopra e infine viene verificato se l'esito prodotto dal
	 * programma corrisponde all'esito atteso.</p>
	 * @param args	Path dei file di input (0) e output (1)
	 */
	public static void main(String[] args) {
		String pathIn = args[0]; 					//primo argomento passato alla consolle
		String pathOut = args[1];					//secondo argomento passato alla consolle
		Path inputPath = Paths.get(pathIn);
		Path outputPath = Paths.get(pathOut);
		if(!Test.checkIn(inputPath)) {				//controlla se l'input è ben-formato
			System.out.println("Bad input file");
			return;									//termina, input non ben-formato
		}
		Test.shuffle(inputPath);					//mescola
		GruppoOrdinabile p = new Puzzle();			//crea un nuovo puzzle vuoto
		p.fill(inputPath);							//preleva tutti i tasselli dal file di input
		p.sort();									//ordina il puzzle
		p.write(outputPath);						//scrivi l'output
	}

}
