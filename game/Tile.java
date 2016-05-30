package game;

/**
 * Created by Oksana on 12/2/2015.
 */
class Tile {

    private int leftSide;
    private int rightSide;

    public Tile() {}

    public Tile(int leftSide, int rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public int getLeftSide() {
        return leftSide;
    }

    public int getRightSide() {
        return rightSide;
    }

    public void printTile() {
        System.out.print(" [" + leftSide + "|" + rightSide + "]");
    }

    public void turnAround() {
        int temp;
        temp = leftSide;
        leftSide = rightSide;
        rightSide = temp;
    }

}
