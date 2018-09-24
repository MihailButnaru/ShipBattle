package exceptions;

public class ShipNotFoundException extends BattleShipException {
    public ShipNotFoundException(String message){
        super(message);
    }
}
