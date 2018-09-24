package ships;

import exceptions.InvalidOperationException;
import model.Coordinates;
import model.Operation;
import model.Orientation;

/**
 * Ship class that stores the ship coordinates, orientation (N, E, S, W) and whether it's been sunk or not.
 *
 * @author Mihail
 */

public class Ship {

    private int x;
    private int y;
    private Orientation orientation;
    private Coordinates coordinates;
    private boolean sunk = false;

    public Ship(int x, int y, Orientation orientation){
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.sunk = false;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void isSink(){
        this.sunk = true;
    }

    public boolean isSunk(){
        return sunk;
    }

    /**
     *  Move the direction of the ship based on where its facing
     */
    public void move() throws InvalidOperationException{
        switch (orientation){
            case N:
                setY(getY() + 1);
                break;
            case E:
                setX(getX() + 1);
                break;
            case S:
                setY(getY() - 1);
                break;
            case W:
                setX(getX() - 1);
                break;
            default:
                throw new InvalidOperationException("Invalid orientation: " + orientation);
        }
    }

    /**
     * Set rotation accordingly to the Operation (M, L, R)
     * @param move
     */
    public void move(Operation move) throws InvalidOperationException {
        switch (move) {
            case M:
                move();
                break;
            case L:
                this.orientation = this.orientation.rotateLeft();
                break;
            case R:
                this.orientation = this.orientation.rotateRight();
                break;
            default:
                throw new InvalidOperationException("Invalid Operation: " + move);
        }
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public Coordinates getCoordinates() {
        return new Coordinates(this.x, this.y);
    }

    public String toString(){
        String result = "(" + getX() + ", " + getY() + ", " + orientation + ")";
        if(isSunk()){
            result += " SUNK";
        }
        return result.trim();
    }
}
