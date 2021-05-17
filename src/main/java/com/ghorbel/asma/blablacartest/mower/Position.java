package com.ghorbel.asma.blablacartest.mower;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x=x;
        this.y=y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveNorth() {
        this.x ++;
    }

    public void moveEst() {
        this.y ++;
    }

    public void moveSouth() {
        this.y --;
    }

    public void moveWest() {
        this.y --;
    }

    public void stepForward(Orientation orientation) {
        switch (orientation) {
        case N:
            this.y ++;
            break;
        case E:
            this.x ++;
            break;
        case S:
            this.y --;
            break;
        case W:
            this.x --;
            break;
        }
    }
}
