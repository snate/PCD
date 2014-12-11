import java.nio.file.Path;
/**
 * <p>Interfaccia che rappresenta gruppi ordinabili di tasselli; naturalmente,
 * estende gruppo dal momento che anche questa rappresenta gruppi di tasselli.</p>
 * <p>Di questi è possibile:</p>
 * <ul>
 *     <li>ordinarli</li>
 *     <li>scrivere i tasselli su un file di output grazie alla struttura ordinata</li>
 * </ul>
 * @author svalle
 */
public interface GruppoOrdinabile extends Gruppo {
	public void sort();
	public void write(Path path);
}
