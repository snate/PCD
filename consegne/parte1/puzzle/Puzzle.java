package puzzle;
import java.nio.file.Path;

/**
 * <p>Classe che implementa un gruppo ordinabile di tasselli.</p>
 * @author svalle
 */
public class Puzzle implements GruppoOrdinabile {
	private int rows;
	private int cols;

	private Gruppo mucchio;
	private PuzzleItem[][] puzzle;

	/**
	 * <p>Classe che implementa un tassello del puzzle.</p>
	 */
	private static class Piece implements PuzzleItem {
		private String id;
		private String car;
		private String north;
		private String south;
		private String east;
		private String west;

		/**
		 * <p>Costruttore</p>
		 * @param str	stringa che contiene id, carattere e id dei vicini
		 */
		public Piece(String[] str) {
			for(int i = 0; i< str.length; i++)
				if(str[i].isEmpty())
					str[i] = null;
			id = str[0];
			car = str[1];
			north = str[2];
			east = str[3];
			south = str[4];
			west = str[5];
		}
		/**
		 * <p>Metodo che restituisce l'id dell'istanza del tassello.</p>
		 * @return	l'id del tassello
		 */
		@Override
		public String getId() {return id;}
		/**
		 * <p>Metodo che restituisce l'id del tassello adiacente sul
		 * lato dir.</p>
		 * @param dir	lato del tassello
		 * @return	id del tassello adiacente sul lato dir
		 */
		@Override
		public String getAdjacent(Dir dir){
			switch(dir.toString()){
			case "n":
				return north;
			case "e":
				return east;
			case "w":
				return west;
			case "s":
				return south;
			}
			return null;
		}
		/**
		 * <p>Metodo che trasforma in stringa il tassello.</p>
		 * @return	il carattere rappresentato dal tassello
		 */
		public String toString() {return car;}
		/**
		 * <p>Metodo col quale si verifica se l'id del tassello è uguale
		 * ad una data stringa str.</p>
		 * @param str	id da confrontare
		 * @return	vero se str è uguale all'id del tassello, altrimenti falso
		 */
		public boolean equals(String str) {return id.equals(str); }
	}

	/**
	 * <p>Factory Method per creare PuzzleItems, una volta che vengono definiti
	 * internamente a Puzzle.</p>
	 * @param str	array di stringhe che caratterizzano un PuzzleItem
	 * @return	un nuovo PuzzleItem
	 */
	public static PuzzleItem createPiece(String[] str){
		return new Piece(str);
	}

	/**
	 * <p>Metodo col quale riempire il gruppo di tasselli
	 * possibilmente disordinato del Puzzle.</p>
	 * @param path	path dove si trova il file di input
	 */
	@Override
	public void fill(Path path) {
		mucchio = new Heap();
		mucchio.fill(path);
	}

	/**
	 * <p>Metodo col quale si calcolano righe e colonne del puzzle.</p>
	 */
	private void setDim(){
		cols = mucchio.conta(createDir("n"));
		rows = mucchio.conta(createDir("e"));
	}

	/**
	 * <p>Metodo col quale si ordina il puzzle seguendo un algoritmo
	 * che scorre le righe o le colonne a seconda che vi siano più righe
	 * o colonne.</p>
	 * <p>Con questo metodo viene riempito la matrice che consente al
	 * puzzle di essere rappresentato in una struttura ordinata.</p>
	 */
	@Override
	public void sort() {
		setDim();
		puzzle = new PuzzleItem[rows][cols];
		String ref = "VUOTO";
		Dir edge = createDir("w");
		int limit = rows;
			if(cols > rows) {
				edge = createDir("n");
				limit = cols;
			}
		for(int i = 0; i < limit; i++) {
			PuzzleItem borderPiece = mucchio.getEdgePiece(edge,edge.init(),ref);
			setEdgePiece(edge, i, borderPiece);
			ref = borderPiece.getId();
		}
		for(int i = 0; i < limit; i++) {
				orderLine(edge,i);
		}
	}

	/**
	 * <p>Metodo che colloca un tassello sul bordo del puzzle.</p>
	 * @param edge	bordo sul quale sta il tassello
	 * @param position	posizione (la prima è 0) del tassello sul bordo
	 * @param piece	tassello da collocare
	 */
	private void setEdgePiece(Dir edge, int position, PuzzleItem piece) {
		if(edge.equals("n"))
			puzzle[0][position] = piece;
		else
			puzzle[position][0] = piece;
	}

	/**
	 * <p>Metodo che ordina un'intera linea (riga o colonna a seconda del
	 * lato top ordinato inizialmente) i-esima.</p>
	 * @param top	lato del quale sono riempite tutte le prime posizioni
	 * @param i	indice che discrimina quale linea ordinare
	 */
	private void orderLine(Dir top, int i) {
		if(top.equals("n"))
			for(int j = 1; j < rows; j++) {
				PuzzleItem item = puzzle[j-1][i];
				String prevId = item.getAdjacent(top.opposite());
				PuzzleItem current = mucchio.getPiece(prevId);
				puzzle[j][i] = current;
			}
		else
			for(int j = 1; j < cols; j++) {
				PuzzleItem item = puzzle[i][j-1];
				String prevId = item.getAdjacent(top.opposite());
				PuzzleItem current = mucchio.getPiece(prevId);
				puzzle[i][j] = current;
			}
	}

	/**
	 * <p>Metodo che scrive su un file di output la matrice ordinata
	 * che rappresenta il puzzle.</p>
	 * @param path	path dove scrivere il file di output
	 */
	@Override
	public void write(Path path){
		String output = "";

		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				output += puzzle[i][j];
		output += "\n\n";

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				output += puzzle[i][j];
				if(j!=cols-1) output += "\t";
			}
			output+="\n";
		}
		output += "\n";

		output += rows + " " + cols;

		InputOutput.writeContent(path, output);
	}

	/**
	 * <p>Metodo che informa quante sono le righe o le colonne</p>
	 * @param side	lato che discrimina righe o colonne
	 * @return	numero di righe o colonne
	 */
	@Override
	public int conta(Dir side){
		if(side.equals("e")) return rows;
		else return cols;
	}

	/**
	 * <p>Metodo che informa se non vi sono tasselli nel GruppoOrdinabile</p>
	 * @return	vero se non vi è alcun tassello, falso altrimenti
	 */
	@Override
	public boolean isEmpty() {
		boolean somethingPresent = false;
		if(!mucchio.isEmpty()) somethingPresent = true;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				if(puzzle[i][j] != null)
					somethingPresent = true;
		return somethingPresent;
	}

	/**
	 * <p>Metodo che restituisce un riferimento ad un tassello del puzzle, se presente,
	 * contenuto nella matrice ordinata che lo rappresenta.</p>
	 * @param id	id del tassello
	 * @return	tassello corrispondente, se presente
	 */
	@Override
	public PuzzleItem getPiece(String id) {
		PuzzleItem piece = mucchio.getPiece(id);
		if(piece != null) return piece;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) {
				piece = puzzle[i][j];
				if(piece.equals(id))
					return piece;
			}
		return null;
	}

	/**
	 * <p>Metodo che restituisce un pezzo sul bordo side adiacente sul lato side
	 * ad un pezzo con id ref.</p>
	 * @param edge	bordo sul quale è situato il pezzo
	 * @param side	lato dove si trova il riferimento adiacente
	 * @param ref	id del tassello adiacente di riferimento
	 * @return	tassello sul bordo edge adiacente sul lato side ad un tassello con id ref
	 */
	@Override
	public PuzzleItem getEdgePiece(Dir edge, Dir side, String ref) {
		PuzzleItem item = mucchio.getEdgePiece(edge, side, ref);
		if(item != null)
			return item;
		int line = 0;
		if(edge.equals("s"))
			line = rows-1;
		if(edge.equals("e"))
			line = cols-1;
		if(edge.equals("n") || edge.equals("s"))
			for(int j = 0; j < cols; j++)
				if((puzzle[line][j].getAdjacent(side)).equals(ref))
					return puzzle[line][j];
		else
			for(int i = 0; i < rows; i++)
				if((puzzle[i][line].getAdjacent(side)).equals(ref))
					return puzzle[i][line];
		return null;
	}

	/**
	 * <p>Factory Method per creare un'istanza di Dir.</p>
	 * <p>Creato per comodità di codifica e per semplificare la lettura
	 * del codice.</p>
	 * @param str	lettera con cui inizializzare l'istanza di Dir
	 * @return	una nuova istanza di Dir
	 */
	private Dir createDir(String str) {return new Dir(str);}
}
