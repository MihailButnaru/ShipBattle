package model;

/**
 * @author Mihail
 */

public enum Orientation {

    N,                  // NORTH
    E,                  // EAST
    S,                  // SOUTH
    W;                  // WEST

    /**
     * Gets the Orientation calling either Left or Right
     * @return Orientation
     */
    public Orientation rotateLeft(){
        switch (this){
            case N : return W;
            case W : return S;
            case S : return E;
            default: return N;
        }
    }

    public Orientation rotateRight(){
        switch (this){
            case N : return E;
            case E : return S;
            case S : return W;
            default: return N;
        }
    }
}
