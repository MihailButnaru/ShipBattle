package controllers;

import exceptions.InvalidOperationException;
import model.Operation;
import ships.*;
import java.util.*;

/**
 * @author Mihail
 */
public class Board {

    private final Ship[][] board;
    public String output;
    public ArrayList<Ship> outputText = new ArrayList<>();

    public Board(int size) {
        board = new Ship[size][size];
    }

    public void allShips(ArrayList<Ship> shipList){
        for(Ship ship: shipList){
            addShip(ship);
        }
    }

    public void setShip(Ship ship){
        board[ship.getX()][ship.getY()] = ship;
    }


    public Ship getShip(int x, int y){
        return board[x][y];
    }
    public void addShip(Ship ship) {
        outputText.add(ship);
        board[ship.getX()][ship.getY()] = ship;
    }

    public void remove(int x, int y) {
        board[x][y] = null;
    }

    /**
     * It moves the ship based on the operation [M, L, R]
     * @param x
     * @param y
     * @param steps
     * @throws InvalidOperationException
     */
    public void moveTheShip(int x, int y, List<Operation> steps) throws InvalidOperationException{
        Ship ship = getShip(x, y);
        if(ship == null){
            return;
        }
        int oldShipX = ship.getX();
        int oldShipY = ship.getY();
        for(int i = 0; i < steps.size(); i++) {
            Operation step = steps.get(i);
            if (step == Operation.M) {
                ship.move(step);
            } else {
                ship.move(step);
            }
        }
        setOutputText(ship.toString());
        remove(oldShipX, oldShipY);

    }

    public void setOutputText(String output){
       this.output = output;
    }
    public String getOutputText(){
        String split = String.format("%n");
        String result = "";
        for(Ship str : outputText){
            result += str + split;
        }
        return result;
    }
    /**
     * Shot Operation, performs to shot a ship at points x and y
     * if there's not a ship at the points x and y then is ignored.
     * @param x
     * @param y
     */

    public void shootTheShip(int x, int y){
        Ship ship = getShip(x,y);
        if(ship != null){
            ship.isSink();
            remove(x,y);
        }
    }
}
