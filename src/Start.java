import controllers.Board;
import exceptions.InvalidOperationException;
import exceptions.ShipNotFoundException;
import io.FileWriter;
import java.io.IOException;

/**
 * @author Mihail
 */
public class Start {

    private static Board board;

    /**
     * @param args
     * @throws IOException
     * @throws ShipNotFoundException
     * @throws InvalidOperationException
     */
    public static void main(String[] args) throws IOException, ShipNotFoundException, InvalidOperationException{
        String inputPath = "input.txt";
        try{
            Game game = new Game();
            game.runGame(inputPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Writes the output into output.txt file
     * @throws IOException
     */
    public static void write() throws IOException{
        FileWriter fileWriter = new FileWriter(run());
        fileWriter.writeFile();
    }

    /**
     * Gets the output
     * @return
     */
    public static String run(){
        return board.getOutputText();
    }

}
