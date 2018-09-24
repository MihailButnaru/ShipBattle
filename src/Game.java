import controllers.Board;
import exceptions.InvalidOperationException;
import exceptions.ShipNotFoundException;
import io.FileWriter;
import io.Parser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mihail
 */

public class Game {

    private static Board board;

    public void runGame(String path) throws IOException, ShipNotFoundException, InvalidOperationException {
        Parser parser = new Parser(path);
        parser.readFile();
        board = new Board(parser.getBoardSize());
        board.allShips(parser.getShipList());


        ArrayList<String> operations = parser.getSpecification();
        for (String steps : operations) {
            parser.getAction(steps);
        }
        write();
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
