package puzzle;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *	<p>PuzzleSolverClient è la classe dove è situata la main procedure del
 *	risolutore di puzzle.</p>
 * @author svalle
 */
public class PuzzleSolverClient {

    /**
     * <p>Questa è la <em>main</em> procedure del PuzzleSolverClient.</p>
     * <p>Viene ricercato nel registro RMI un GruppoOrdinabile per poter eseguire grazie a questo la risoluzione del
     * puzzle.</p>
     * @param args	Path dei file di input (0) e output (1) e nome del server (2)
     */
    public static void main(String[] args) {
        String pathIn = args[0]; 					//primo argomento passato alla consolle
        String pathOut = args[1];					//secondo argomento passato alla consolle
        String nomeServer = args[2];					//terzo argomento passato alla consolle
        Path inputPath = Paths.get(pathIn);
        Path outputPath = Paths.get(pathOut);
        if(!Test.checkIn(inputPath)) {				//controlla se l'input è ben-formato
            System.out.println("Bad input file");
            return;									//termina, input non ben-formato
        }
        Test.shuffle(inputPath);					//mescola
        ArrayList<String> rows = InputOutput.readContent(inputPath); //crea input per puzzle
        String obj = "rmi://" + nomeServer + "/Puzzle";
        GruppoOrdinabile p = null;
        try { p = (GruppoOrdinabile) RMIBinder.richiedi(obj); }
        catch (Exception e) {
          System.out.println("Risolutore non trovato");
        }
        int attempts = 0;
        Thread[] attesa = new Thread[7];
        for(int i = 0; i < 7; i++)
          attesa[i] = new Thread() {
            public void run() {
              try { Thread.sleep(5000); } catch (InterruptedException ie) {}
            }
          };
          boolean error = false;
        if(p!= null) {
          while(attempts < 7) {
            try {
              p.fill(rows);     //preleva tutti i tasselli dal file di input
              attempts = 7;
            }
            catch (RemoteException re) {
              System.out.println("Errore di comunicazione durante il riempimento del puzzle");
              attempts++; attesa[attempts].start();
            try { attesa[attempts].join(); } catch (InterruptedException ie) {}
              if(attempts == 7) error = true;
            }
          }
          if(!error) attempts = 0;
          while(attempts < 7) {
            try {
              p.sort();       //ordina il puzzle
              attempts = 7;
            }
            catch (RemoteException re) {
              System.out.println("Errore di comunicazione durante l'ordinamento del puzzle");
              attempts++; attesa[attempts].start();
              try { attesa[attempts].join(); } catch (InterruptedException ie) {}
              if(attempts == 7) error = true;
            }
          }
          if(!error) attempts = 0;
          while(attempts < 7) {
            try {
              InputOutput.writeContent(outputPath,p.outcome());   //scrivi l'output
              attempts = 7;
            }
            catch (RemoteException re) {
              System.out.println("Errore di comunicazione durante il recupero del risultato");
              attempts++; attesa[attempts].start();
              try { attesa[attempts].join(); } catch (InterruptedException ie) {}
              if(attempts == 7) error = true;
            }
          }
          if(error)
            System.out.println("A causa di errori di comunicazione, non e' stato possibile risolvere il puzzle");
        }
        
    }

}
