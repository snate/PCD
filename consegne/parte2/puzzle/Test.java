package puzzle;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * <p>La classe Test offre funzionalità per eseguire dei test sui file
 * di input e output.</p>
 * @author svalle
 */
public class Test {
	private int rows;
	private int cols;
	private String content;
	private Pair[][] map;
	/**
	 * <p>La classe Pair rappresenta la coppia {letter,times}, dove letter
	 * è un certo carattere UTF-8 e times è un numero che indica che è
	 * la times-esima volta che si legge letter dal file di input.</p>
	 */
	private static class Pair {
		private int times = 1;
		private String letter;
		/**
		 * <p>Costruttore di Pair</p>
		 * @param s	Carattere UTF-8 col quale si inizializza letter
		 */
		public Pair(String s) { letter = s; }
		/**
		 * <p>Costruttore di Pair</p>
		 * @param s	Carattere UTF-8 col quale si inizializza letter
		 * @param n Numero di volte che si ha incontrato il carattere s
		 */
		public Pair(String s,int n) { letter = s; times = n; }
		/**
		 * <p>Metodo che fornisce l'id di un'istanza di Pair.</p>
		 * @return	l'id formato dalla coppia {lettera,occorrenze}
		 */
		public String toString() { return letter+times; }
		/**
		 * <p>Metodo che restituisce il carattere UTF-8 presente nel field letter.</p>
		 * @return	il carattere UTF-8 relativo ad una certa istanza di Pair
		 */
		public String getLetter() { return letter; }
		/**
		 * <p>Metodo che dice a quale n-esima occorrenza di letter corrisponde
		 * un certo pair.</p>
		 * @return	Il numero di occorrenze di letter incontrate finora
		 */
		public int getTimes() { return times+1; }
	};
	private ArrayList<Pair> encountered = new ArrayList<Pair>();
	private final static Path outcome = Paths.get("test");

	/**
	 * <p>Metodo che costruisce un puzzle a partire da un testo scritto
	 * in una sola riga. Restituisce l'indirizzo del file dove ha salvato i tasselli.</p>
	 * @param inputPath	Path dal quale ottenere il file di input
	 * @return	l'indirizzo dove sono stati salvati i tasselli del puzzle
	 */
	public Path build(Path inputPath) {
		content = InputOutput.readContent(inputPath).get(0);
		setDim();
		map = new Pair[rows][cols];
		for(int i = 0; i < content.length() % cols; i++)
			content += " ";
		String[] lines = new String[rows];
		for(int i = 0; i < rows; i++) {
			lines[i] = content.substring(0+cols*i, cols*(i+1));
		}
		make(lines);
		return outcome;
	}

	/**
	 * <p>Metodo che imposta le dimensioni che assumerà il puzzle
	 * in funzione della dimensione della riga letta, memorizzata
	 * nel field content.</p>
	 */
	private void setDim() {
		int dim = content.length();
		boolean square = false;
		while(rows*rows < dim && !square) {
			rows++;
			if(rows*rows == dim) square = true;
		}
		if(dim > rows*(rows-1))
			cols = rows;
		else
			cols = rows--;
	}

	/**
	 * <p>Metodo che scrive i tasselli sul file che il main utilizzerà per
	 * riempire il puzzle.</p>
	 * @param lines	array contenente l'insieme dei tasselli giacenti su una stessa riga del puzzle
	 */
	private void make(String[] lines) {
		String inputForMain = "";
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) {
				String s = lines[i].substring(j,j+1);
				map[i][j] = getPair(s);						//costruisce una mappa degli ID
			}
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) {
				inputForMain += map[i][j] + "\t";
				inputForMain += lines[i].charAt(j) + "\t";
				//tutti questi controlli verificano se il tassello è sul bordo o meno
				if(i == 0)
					inputForMain += "VUOTO" + "\t";
				else
					inputForMain += map[i-1][j] + "\t";
				if(j == cols-1)
					inputForMain += "VUOTO" + "\t";
				else
					inputForMain += map[i][j+1] + "\t";
				if(i == rows-1)
					inputForMain += "VUOTO" + "\t";
				else
					inputForMain += map[i+1][j] + "\t";
				if(j == 0)
					inputForMain += "VUOTO" + "\t";
				else
					inputForMain += map[i][j-1];
				//questo controllo invece si preoccupa di non aggiungere il ritorno a capo
				//all'ultimo tassello del puzzle
				if(i < rows-1 || j < cols-1)
					inputForMain += "\n";
		}
		InputOutput.writeContent(outcome,inputForMain);
	}

	/**
	 * <p>Questo metodo restituisce la coppia letter-times di un tassello come descritto
	 * in Pair; il calcolo è effettuato cercando le precedenti occorrenze del carattere s.</p>
	 * @param s	carattere UTF-8 del tassello
	 * @return	l'id del tassello
	 */
	private Pair getPair(String s) {
		Iterator<Pair> it = encountered.iterator();
		Pair p = null;
		while(it.hasNext()) {
			Pair current = it.next();
			if(s.equals(current.getLetter())) {
				int n = current.getTimes() + 1;
				p = new Pair(s,n);
			}
		}
		if(p == null)								//è la prima occorrenza del carattere s
			p = new Pair(s);
		encountered.add(p);
		return p;
	}

	/**
	 * <p>Questo metodo esegue un controllo di integrità sull'input contenente
	 * i tasselli del puzzle, accertandosi che ogni riga abbia sei stringhe divise
	 * da un carattere di tabulazione.</p>
	 * @param inputPath	Path dove è situato il file di input
	 * @return	Vero se l'input è ben formato, altrimenti falso
	 */
	public static boolean checkIn(Path inputPath) {
		ArrayList<String> input = InputOutput.readContent(inputPath);
		Iterator<String> it = input.iterator();
		while(it.hasNext()) {
			String line = it.next();
			String[] piece = line.split("\\t",-1);	//divide la riga splittando rispetto al carattere di tabulazione
			if(piece.length != 6) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Metodo che mescola casualmente i tasselli presenti nel file di input.</p>
	 * @param path	Path dove è situato il file di input
	 */
	public static void shuffle(Path path) {
		ArrayList<String> input = InputOutput.readContent(path);
		int n = input.size();
		ArrayList<String> shuffled = new ArrayList<String>();
		for(int i = 0; i < n; i++) {
			int row = randInt(input.size());
			String elem = input.get(row);
			shuffled.add(i, elem);
			input.remove(row);
		}
		Iterator<String> it = shuffled.iterator();
		String output = "";
		while(it.hasNext())
			output += it.next() + "\n";					//ricrea un testo con i tasselli mescolati su varie righe
		InputOutput.writeContent(path, output);			//riscrive il file con il testo precedentemente creato
	}

	/**
	 * <p>Metodo che restituisce un intero tra 0 e n.</p>
	 * @param n	limite superiore dell'intervallo fornito
	 * @return	numero pseudocasuale tra 0 e n
	 */
	private static int randInt(int n) {
		Random rand = new Random();
		return rand.nextInt(n);
	}

	/**
	 * <p>Metodo che controlla se il file prodotto dalla main procedure
	 * corrisponde alle attese, avendo creato il puzzle da un input formato
	 * da una sola riga.</p>
	 * @param outputPath	path dove è situato il file di output
	 * @return	vero se la main procedure ha funzionato correttamente, altrimenti falso
	 */
	public boolean checkOut(Path outputPath) {
		boolean corretto = true;
		ArrayList<String> prodotto = InputOutput.readContent(outputPath);
		String confronto;
		if(!content.equals(prodotto.get(0)))		//controlla primo pezzo di output
			corretto = false;
		for(int i = 0; i < rows; i++){				//controlla secondo pezzo di output
			confronto = "";
			for(int j = 0; j < cols; j++) {
				confronto += map[i][j].getLetter();
				if(j != cols-1)
					confronto += "\t";
			}
			if(!confronto.equals(prodotto.get(2+i)))
				corretto = false;
		}
		confronto = rows + " " + cols;				//ultima riga: numero righe - spazio - numero colonne
		if(!confronto.equals(prodotto.get(3+rows))) //controlla terzo pezzo di output
			corretto = false;
		return corretto;
	}

	public static void main(String[] args) {
		String pathIn = args[0]; 					//primo argomento passato alla consolle
		String pathOut = args[1];					//secondo argomento passato alla consolle
		Path inputPath = Paths.get(pathIn);
		Path outputPath = Paths.get(pathOut);
		Test test = new Test();
		inputPath = test.build(inputPath);
		PuzzleSolver.main(args);
		if(test.checkOut(outputPath))
			System.out.println("OK");
		else
			System.out.println("ERRORE");
	}
}
