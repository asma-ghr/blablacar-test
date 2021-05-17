package com.ghorbel.asma.blablacartest.lawn;

import com.ghorbel.asma.blablacartest.exception.IllegalPositionException;
import com.ghorbel.asma.blablacartest.mower.Position;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class to represent the lawn by its limit position and the positions occupied by mowers
 */
public class Lawn {
    /**
     * Upper right corner position
     */
    Position limitPosition;

    /**
     * Set of positions occupied by mowers in the lawn
     */
    Set<Position> busyPositions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public Lawn(int limitX, int limitY) throws IllegalPositionException {
        if (limitX < 0 || limitY < 0) {
            throw new IllegalPositionException(String.format("Lawn input limit coordinates are (%d, %d). Illegal negative coordinate.", limitX, limitY));
        }
        this.limitPosition = new Position(limitX, limitY);
    }

    public void addBusyPosition(Position position) {
        busyPositions.add(position);
    }

    public void removeBusyPosition(Position position) {
        busyPositions.remove(position);
    }

    /**
     * Removes old busy position from the set and replace it with new busy position after a mower move.
     * @return
     */
    public void updateBusyPositions(Position oldPosition, Position newPosition) {
        removeBusyPosition(oldPosition);
        addBusyPosition(newPosition);
    }

    /**
     * Returns the set of busy positions in the lawn. A busy position, is a position already occupied by a mower.
     * @return set of busy positions
     */
    public Set<Position> getBusyPositions() {
        return busyPositions;
    }

    /**
     *
     * Checks if a position is inside lawn
     * @param position - the position to check
     * @return true if position is inside lawn; false otherwise
     */
    public boolean validatePosition(Position position) {
        return (position.getX() >= 0 && position.getY() >= 0 && position.getX() <= limitPosition.getX() && position.getY() <= limitPosition.getY());
    }
}
