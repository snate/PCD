package puzzle;
/**
 * @author svalle
 */
public class Dir {
	private static String[] DIR = {"n", "e", "w", "s"};
	private String dir;
	public Dir(String d) { dir = d; }
	public Dir(int index) { dir = DIR[index]; }
	public String toString() {return dir;}
	public boolean equals(String s) { return dir.equals(s);	}
		public Dir next() {
			switch(dir){
			case "w": return new Dir("s");
			case "e": return new Dir("n");
			case "n": return new Dir("e");
			case "s": return new Dir("w");
			}
			return null;
		}
		public Dir init() {
			switch(dir.toString()){
			case "n": return new Dir("w");
			case "e": return new Dir("s");
			case "s": return new Dir("e");
			case "w": return new Dir("n");
			}
			return null;
		}
		public Dir opposite() {
			switch(dir.toString()){
			case "n": return new Dir("s");
			case "e": return new Dir("w");
			case "s": return new Dir("n");
			case "w": return new Dir("e");
			}
			return null;
		}
}
