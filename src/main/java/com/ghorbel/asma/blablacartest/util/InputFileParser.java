package com.ghorbel.asma.blablacartest.util;

import com.ghorbel.asma.blablacartest.exception.FileParserException;
import com.ghorbel.asma.blablacartest.exception.IllegalPositionException;
import com.ghorbel.asma.blablacartest.lawn.Instruction;
import com.ghorbel.asma.blablacartest.lawn.Lawn;
import com.ghorbel.asma.blablacartest.lawn.MowerMover;
import com.ghorbel.asma.blablacartest.mower.Mower;
import com.ghorbel.asma.blablacartest.mower.Orientation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputFileParser {
    private final BufferedReader bufferedReader;
    private final Lawn lawn;
    private final List<MowerMover> mowerMoverList;
    private int lineIndex;

    public InputFileParser(String fileName) throws IOException, FileParserException, IllegalPositionException {
        FileReader input = new FileReader(fileName);
        this.bufferedReader = new BufferedReader(input);
        this.lineIndex = 0;
        lawn = createLawn();
        mowerMoverList = createMowerMovers();
        // Fill busy position list with mower initial positions
        for(MowerMover mowerMover : mowerMoverList) {
            if(lawn.getBusyPositions().contains(mowerMover.getMower().getPosition())) {
               throw new FileParserException("Unsupported duplicate position");
            }
            lawn.addBusyPosition(mowerMover.getMower().getPosition());
        }
    }

    public List<MowerMover> getMowerMoverList() {
        return mowerMoverList;
    }

    private Lawn createLawn() throws IOException, FileParserException, IllegalPositionException {
        String line = bufferedReader.readLine();
        if (line == null || line.isEmpty()) {
            throw new FileParserException("Issue at line 0");
        }
        String[] coordinates = line.split(" ");
        if (coordinates.length != 2) {
            throw new FileParserException("Issue at line 0");
        }
        return new Lawn(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
    }

    private List<MowerMover> createMowerMovers() throws IOException, FileParserException, IllegalPositionException {
        String line;
        List<MowerMover> mowerMoverlist = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            lineIndex ++;
            // check to make sure you have valid data
            String[] mowerPosition = line.split(" ");
            if(mowerPosition.length != 3) {
                throw new FileParserException(String.format("Issue at line %d", lineIndex));
            }
            // read instructions
            line = bufferedReader.readLine();
            List<Instruction> instructionList = readInstructions(line);
            lineIndex++;
            mowerMoverlist.add(new MowerMover(new Mower(Integer.valueOf(mowerPosition[0]), Integer.valueOf(mowerPosition[1]),
                    Orientation.valueOf(mowerPosition[2]), instructionList), lawn));
        }
        return mowerMoverlist;
    }

    private List<Instruction> readInstructions(String line) throws FileParserException {
        if (line == null) {
            throw new FileParserException(String.format("Instructions unavailable for mower at line %d", lineIndex));
        }
        List<Instruction> instructionList = new ArrayList<>();
        for(int i=0; i<line.length(); i++) {
            instructionList.add(Instruction.valueOf(Character.toString(line.charAt(i))));
        }
        return instructionList;
    }

}
