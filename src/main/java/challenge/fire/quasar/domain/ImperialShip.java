package challenge.fire.quasar.domain;

public class ImperialShip {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ImperialShip{" +
                "position=" + position +
                '}';
    }
}
