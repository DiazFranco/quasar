package challenge.fire.quasar.domain;

public class Hauler extends ImperialShip{

    private String message;

    public Hauler(Position position, String message) {
        this.setPosition(position);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
