package puzzle;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>Questa classe implementa un gruppo di tasselli possibilmente disordinati.</p>
 * @author svalle
 */
public class Heap implements Gruppo {
	private ArrayList<PuzzleItem> mucchio = new ArrayList<PuzzleItem>();

	/**
	 * <p>Il metodo conta restituisce quanti tasselli contiene il gruppo
	 * sul bordo side.</p>
	 * @param side	bordo (nord/sud/ovest/est)
	 * @return	numero di tasselli sul bordo side
	 */
	@Override
	public int conta(Dir side) {
		int x = 0;
		Iterator<PuzzleItem> it = mucchio.iterator();
		while(it.hasNext()){
			PuzzleItem item = it.next();
			if(item.getAdjacent(side).equals("VUOTO")) x++;
		}
		return x;
	}

	/**
	 * <p>Metodo che restituisce, se presente, un tassello nel gruppo avente
	 * un certo id.</p>
	 * @param id	id del tassello ricercato
	 * @return	tassello con l'id ricercato
	 */
	@Override
	public PuzzleItem getPiece(String id) {
		Iterator<PuzzleItem> it = mucchio.iterator();
		while(it.hasNext()) {
			PuzzleItem item = it.next();
			if(item.equals(id)) {
				it.remove();
				return item;
			}
		}
		return null;
	}

	/**
	 * <p>Metodo che restituisce un tassello sul bordo avente un vicino sul lato
	 * refSide con id ref.</p>
	 * @param edge	bordo sul quale è situato il tassello
	 * @param refSide	lato sul quale si trova il vicino del tassello ricercato
	 * @param ref	id del tassello vicino
	 * @return	tassello ricercato
	 */
	@Override
	public PuzzleItem getEdgePiece(Dir edge, Dir refSide, String ref){
		Iterator<PuzzleItem> it = mucchio.iterator();
		while(it.hasNext()){
			PuzzleItem item = it.next();
			String border = item.getAdjacent(edge);
			String closeRef = item.getAdjacent(refSide);
			if(border.equals("VUOTO") && closeRef.equals(ref)){
				it.remove();
				return item;
			}
		}
		return null;
	}

	/**
	 * <p>Metodo che riempie il gruppo da un file di input contenente i tasselli.</p>
	 * @param path	path dove si trova il file di input
	 */
	@Override
	public void fill(Path path) {
		ArrayList<String> rows = InputOutput.readContent(path);
		Iterator<String> ita = rows.iterator();
		while(ita.hasNext()){
			String line = ita.next();
			String[] piece = line.split("\\t",-1);
			PuzzleItem item = Puzzle.createPiece(piece);
			mucchio.add(item);
		}
	}

	/**
	 * <p>Metodo che informa se non vi è alcun tassello nel gruppo.</p>
	 * @return	vero se non vi è alcun tassello nel gruppo, altrimenti falso
	 */
	public boolean isEmpty(){
		return mucchio.isEmpty();
	}
}
