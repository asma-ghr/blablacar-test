package com.ghorbel.asma.blablacartest.mower;

import com.ghorbel.asma.blablacartest.lawn.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Mower {
    private Position position;
    private Orientation orientation;
    private List<Instruction> instructionList;

    public Mower(Position position, Orientation orientation, List<Instruction> instructionList) {
        this.position = position;
        this.orientation = orientation;
        this.instructionList = instructionList;
    }

    public Mower(int x, int y, Orientation orientation, List<Instruction> instructionList ) {
        this(new Position(x, y), orientation, instructionList);
    }

    public Mower(int x, int y, Orientation orientation) {
        this(new Position(x, y), orientation, new ArrayList<>());
    }

    public Position getPosition() {
        return this.position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public void addInstruction(Instruction instruction) {
        instructionList.add(instruction);
    }

    public void moveMower(Position newPosition) {
        this.position = newPosition;
    }

    public void turnRight() {
        this.orientation = Orientation.values()[(this.orientation.ordinal() + 1) % 4];
    }

    public void turnLeft() {
        if(this.orientation.ordinal() == 0) {
            this.orientation = Orientation.values()[3];
        } else {
            this.orientation = Orientation.values()[(this.orientation.ordinal() - 1)];
        }
    }

    public void moveForward() {
        this.position.stepForward(this.orientation);
    }

    @Override
    public String toString() {
        return position.getX() + " " + position.getY() + " " + orientation;
    }
}
