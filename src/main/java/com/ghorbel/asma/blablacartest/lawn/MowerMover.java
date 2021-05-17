package com.ghorbel.asma.blablacartest.lawn;

import com.ghorbel.asma.blablacartest.exception.IllegalPositionException;
import com.ghorbel.asma.blablacartest.mower.Mower;
import com.ghorbel.asma.blablacartest.mower.Position;

public class MowerMover implements Runnable {
    Mower mower;
    Lawn lawn;

    public MowerMover(Mower mower, Lawn lawn) throws IllegalPositionException {
        if (!lawn.validatePosition(mower.getPosition())) {
            throw new IllegalPositionException("Mower position is outside lawn. Check if it is negative or if it exceeds lawn boundaries");
        }
        this.mower = mower;
        this.lawn = lawn;
    }

    public Mower getMower() {
        return mower;
    }

    private void checkNewPostionAndMoveForward() {
        // Simulate next move to get next position
        Position nextPosition = mower.getPosition();
        nextPosition.stepForward(mower.getOrientation());
        if (lawn.validatePosition(nextPosition)) {
            // check eligibility to move then move forward
            synchronized (lawn.getBusyPositions()) {
                if (!lawn.getBusyPositions().contains(nextPosition)) {
                    lawn.updateBusyPositions(mower.getPosition(), nextPosition);
                    mower.moveForward();
                }
            }
        }
    }

    private void moveMower(Instruction instruction) {
        switch (instruction) {
        case R:
            mower.turnRight();
            break;
        case L:
            mower.turnLeft();
            break;
        case F:
            checkNewPostionAndMoveForward();
            break;
        }
    }

    @Override
    public void run() {
        for(Instruction instruction : mower.getInstructionList()) {
            moveMower(instruction);
        }
    }
}
