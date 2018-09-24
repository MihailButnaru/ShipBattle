import exceptions.InvalidOperationException;
import model.Coordinates;
import model.Operation;
import model.Orientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ships.Ship;

/**
 * Ship Rotation Left and Right Testing
 * @author Mihail Butnaru
 */
public class ShipTest {
    @Test
    public void shipRotationRight() throws InvalidOperationException{

        Ship ship1 = new Ship(0,0, Orientation.N);
        Ship ship2 = new Ship(1,1, Orientation.E);
        Ship ship3 = new Ship(2,2, Orientation.S);
        Ship ship4 = new Ship(1,2, Orientation.W);
        Ship ship5 = new Ship(2,1, Orientation.S);

        ship1.move(Operation.R);
        ship2.move(Operation.R);
        ship3.move(Operation.R);
        ship4.move(Operation.R);
        ship5.move(Operation.R);

        assertEquals(ship1.getOrientation(), Orientation.E);
        assertEquals(ship2.getOrientation(), Orientation.S);
        assertEquals(ship3.getOrientation(), Orientation.W);
        assertEquals(ship4.getOrientation(), Orientation.N);
        assertEquals(ship5.getOrientation(), Orientation.W);

    }
    @Test
    public void shipRotateLeft() throws InvalidOperationException{
        Ship ship1 = new Ship(0,0, Orientation.N);
        Ship ship2 = new Ship(1,1, Orientation.E);
        Ship ship3 = new Ship(2,2, Orientation.S);
        Ship ship4 = new Ship(1,2, Orientation.W);
        Ship ship5 = new Ship(2,1, Orientation.S);

        ship1.move(Operation.L);
        ship2.move(Operation.L);
        ship3.move(Operation.L);
        ship4.move(Operation.L);
        ship5.move(Operation.L);

        assertEquals(ship1.getOrientation(), Orientation.W);
        assertEquals(ship2.getOrientation(), Orientation.N);
        assertEquals(ship3.getOrientation(), Orientation.E);
        assertEquals(ship4.getOrientation(), Orientation.S);
        assertEquals(ship5.getOrientation(), Orientation.E);
    }

}
