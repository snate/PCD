/**
 * @author svalle
 */
public interface PuzzleItem {
	public String getId();
	public String getAdjacent(Dir dir);
	public boolean equals(String str);
}
