/**
 * @author svalle
 *
 */
public interface Clump {
	public Piece getPiece(String id);
	public void fill(Object item);
	public void write(String path);
}
