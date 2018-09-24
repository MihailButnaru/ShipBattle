import controllers.Board;
import exceptions.InvalidOperationException;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.Orientation;
import org.junit.jupiter.api.Test;
import ships.Ship;


public class BoardTest {
    @Test
    public void shotTest() throws InvalidOperationException{

        Board board = new Board(10);
        Ship ship1 = new Ship(3,4, Orientation.N);
        Ship ship2 = new Ship(1,2, Orientation.S);
        board.setShip(ship1);
        board.setShip(ship2);
        board.shootTheShip(3,4);
        assertNull(board.getShip(3,4));
        assertTrue(ship1.isSunk());

    }
}
