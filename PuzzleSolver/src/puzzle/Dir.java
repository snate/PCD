package puzzle;
/**
 * <p>Classe utilizzata per rappresentare i punti cardinali.</p>
 * @author svalle
 */
public class Dir {
	private static String[] DIR = {"n", "e", "w", "s"};
	private final String dir;
	/**
	 * <p>Costruttore di Dir</p>
	 * @param d	lettera che indica a quale punto cardinale corrisponde una certa istanza di Dir
	 */
	public Dir(String d) { dir = d; }
	/**
	 * <p>Costruttore di Dir</p>
	 * @param index	numero che crea inizializza un'istanza di Dir grazie all'array privato DIR
	 */
	public Dir(int index) { dir = DIR[index]; }
	/**
	 * <p>Metodo che restituisce l'iniziale del punto cardinale di una certa istanza
	 * di Dir.</p>
	 * @return	l'iniziale del punto cardinale di una certa istanza di Dir
	 */
	public String toString() {return dir;}
	/**
	 * <p>Metodo di confronto</p>
	 * @param s	lettera da confrontare con l'iniziale del punto cardinale rappresentato
	 * @return	vero se corrisponde alla stessa direzione, altrimenti falso
	 */
	public boolean equals(String s) { return dir.equals(s);	}
	/**
	 * <p>Metodo che fornisce la direzione lungo la quale proseguire
	 * partendo dalla direzione rappresentata dall'istanza.</p>
	 * @return	direzione lungo la quale procedere
	 */
	public Dir next() {
		switch(dir){
			case "w":
				return new Dir("s");
			case "e":
				return new Dir("n");
			case "n":
				return new Dir("e");
			case "s":
				return new Dir("w");
			default:
				return null;
		}
	}
	/**
	 * <p>Metodo che fornisce una direzione verso cui rivolgersi al primo
	 * passo dell'algoritmo.</p>
	 * @return	direzione di riferimento iniziale
	 */
	public Dir init() {
		switch(dir.toString()) {
			case "n":
				return new Dir("w");
			case "e":
				return new Dir("s");
			case "s":
				return new Dir("e");
			case "w":
				return new Dir("n");
			default:
				return null;
		}
	}
	/**
	 * <p>Metodo che restituisce il punto cardinale opposto</p>
	 * @return	istanza di Dir rappresentante il punto cardinale opposto
	 */
	public Dir opposite() {
		switch(dir.toString()){
			case "n":
				return new Dir("s");
			case "e":
				return new Dir("w");
			case "s":
				return new Dir("n");
			case "w":
				return new Dir("e");
			default:
				return null;
		}
	}
}
