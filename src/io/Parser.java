package io;

import controllers.Board;
import exceptions.InvalidOperationException;
import exceptions.ShipNotFoundException;
import model.Operation;
import model.Orientation;
import ships.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Mihail
 */

public class Parser {

    /**
     * Regex expressions:
     *  Ship: digit [0-9], [0-9] and single character range [A-Z]
     *  Move: digit [0-9], [0-9] and single character [M, R, L]
     *  Shot: digit [0-9], [0-9]
     */
    private static final Pattern SHIPS_PATTERN = Pattern.compile("\\(\\s*([0-9]+)\\s*,\\s*([0-9]+), ([A-Z])\\)");
    private static final Pattern MOVE_PATTERN = Pattern.compile("\\(\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*\\)\\s*([MRL]+)");
    private static final Pattern SHOT_PATTERN = Pattern.compile("\\(\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*\\)");

    public ArrayList<Ship> listOfShips;
    public ArrayList<String> specification;
    public String path;
    public int size;
    public Board board;

    public Parser(String path){
        this.path = path;
        listOfShips = new ArrayList<>();
        specification = new ArrayList<>();
    }
    public Parser(){}

    /**
     * Read the file
     * Parses the input line from the file and it assigns the correct operations
     *
     * @throws IOException
     */
    public void readFile() throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(path));
        int count = 0;
        try{
            String line;
            while((line = br.readLine()) != null){
                if(count == 0){
                    size = Integer.parseInt(line);
                    board = new Board(size);
                }else if(count == 1){
                    listOfShips = battleShips(line);
                }else{
                    specification.add(line);
                }
                count++;
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    /**
     * Performs the operation on the board
     * It sets the ships on the given points [x, y and the orientation[N, S, E, W]]
     * @param line
     * @return
     */
    private ArrayList<Ship> battleShips(String line){

        Matcher patternMatch = SHIPS_PATTERN.matcher(line);
        ArrayList<Ship> listShips = new ArrayList<>();
        while(patternMatch.find()){
            int x = Integer.parseInt(patternMatch.group(1));
            int y = Integer.parseInt(patternMatch.group(2));
            Orientation orientation = Orientation.valueOf(patternMatch.group(3));
            Ship ship = new Ship(x, y, orientation);
            board.addShip(ship);
            listShips.add(ship);
        }
        return listShips;
    }

    /**
     * List of Ships
     * @return listOfShips
     */
    public ArrayList<Ship> getShipList() throws ShipNotFoundException{
        return listOfShips;
    }

    /**
     * List of Ships and Instructions [Move, Left, Right]
     * @return operations
     */
    public ArrayList<String> getSpecification() throws ShipNotFoundException{
        return specification;
    }

    /**
     * Size of the board
     * @return size
     */
    public int getBoardSize(){
        return size;
    }

    /**
     * Performs the move and shot operation
     * Move operation is performed by a sequences of instructions at the given points [x,y]
     * Shot operation is performed by attempted to shot a ship at the given points [x,y]
     * @param line
     */
    public void getAction(String line) throws ShipNotFoundException, InvalidOperationException{

        Matcher moveMatch = MOVE_PATTERN.matcher(line);
        Matcher shootMatch = SHOT_PATTERN.matcher(line);

        if(moveMatch.find()){
            int x = Integer.valueOf(moveMatch.group(1));
            int y = Integer.valueOf(moveMatch.group(2));
            String moveSteps = moveMatch.group(3);
            List<Operation> moves = IntStream.range(0, moveSteps.length()).mapToObj(moveSteps::charAt)
                    .map(String::valueOf).map(Operation::valueOf).collect(Collectors.toList());
            board.moveTheShip(x,y,moves);

        }else if(shootMatch.find()){
            int x = Integer.valueOf(shootMatch.group(1));
            int y = Integer.valueOf(shootMatch.group(2));
            board.shootTheShip(x,y);
        }
    }



}
