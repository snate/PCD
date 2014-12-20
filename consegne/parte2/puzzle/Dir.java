package puzzle;
/**
 * <p>Classe utilizzata per rappresentare i punti cardinali.</p>
 * @author svalle
 */
public class Dir {
	private enum CDir { NORTH, EAST, WEST, SOUTH }
	private CDir dir;
	/**
	 * <p>Costruttore di Dir</p>
	 * @param d	lettera che indica a quale punto cardinale corrisponde una certa istanza di Dir
	 */
	public Dir(String d) {
		if(d.equals("n"))
			dir = CDir.NORTH;
		if(d.equals("e"))
			dir = CDir.EAST;
		if(d.equals("w"))
			dir = CDir.WEST;
		if(d.equals("s"))
			dir = CDir.SOUTH;
	}
	/**
	 * <p>Costruttore di Dir</p>
	 * @param d	Costante enumerativa che rappresenta uno dei quattro punti cardinali
	 */
	private Dir(CDir d) {dir = d;}
	/**
	 * <p>Metodo che restituisce l'iniziale del punto cardinale di una certa istanza
	 * di Dir.</p>
	 * @return	l'iniziale del punto cardinale di una certa istanza di Dir
	 */
	public String toString() {
		switch(dir) {
			case NORTH: return new String("n");
			case EAST: return new String("e");
			case WEST: return new String("w");
			case SOUTH: return new String("s");
		}
		return null;
	}
	/**
	 * <p>Metodo di confronto</p>
	 * @param s	lettera da confrontare con l'iniziale del punto cardinale rappresentato
	 * @return	vero se corrisponde alla stessa direzione, altrimenti falso
	 */
	public boolean equals(String s) { return (dir.toString()).equals(s);	}
	/**
	 * <p>Metodo che fornisce la direzione lungo la quale proseguire
	 * partendo dalla direzione rappresentata dall'istanza.</p>
	 * @return	direzione lungo la quale procedere
	 */
	public Dir next() {
		switch(dir){
			case NORTH:
				return new Dir(CDir.EAST);
			case EAST:
				return new Dir(CDir.NORTH);
			case WEST:
				return new Dir(CDir.SOUTH);
			case SOUTH:
				return new Dir(CDir.WEST);
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
		switch(dir) {
			case NORTH:
				return new Dir(CDir.WEST);
			case EAST:
				return new Dir(CDir.SOUTH);
			case WEST:
				return new Dir(CDir.NORTH);
			case SOUTH:
				return new Dir(CDir.EAST);
			default:
				return null;
		}
	}
	/**
	 * <p>Metodo che restituisce il punto cardinale opposto</p>
	 * @return	istanza di Dir rappresentante il punto cardinale opposto
	 */
	public Dir opposite() {
		switch (dir) {
			case NORTH:
				return new Dir(CDir.SOUTH);
			case EAST:
				return new Dir(CDir.WEST);
			case WEST:
				return new Dir(CDir.EAST);
			case SOUTH:
				return new Dir(CDir.NORTH);
			default:
				return null;
		}
	}
}
