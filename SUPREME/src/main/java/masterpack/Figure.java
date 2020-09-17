package masterpack;

public abstract class Figure {
    protected int positionX;
    protected int positionY;

    public Figure() {

    }

    public void move(Moves move, int step) {
        switch (move) {
            case UP: {
                positionY += step;
                break;
            }
            case DOWN: {
                positionY -= step;
                break;
            }
            case LEFT: {
                positionX -= step;
                break;
            }
            case RIGHT: {
                positionX += step;
                break;
            }
            default:
                break;
        }
    }

}
