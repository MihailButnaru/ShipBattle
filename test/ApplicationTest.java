import exceptions.InvalidOperationException;
import exceptions.ShipNotFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for the start of the application
 *
 */
public class ApplicationTest {

    @Test
    public void validInput() throws InvalidOperationException, IOException, ShipNotFoundException{

        Game game = new Game();
        game.runGame("input.txt");
        System.out.println(game);



    }
}
