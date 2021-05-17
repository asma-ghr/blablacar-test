package com.ghorbel.asma.blablacartest;

import com.ghorbel.asma.blablacartest.exception.FileParserException;
import com.ghorbel.asma.blablacartest.exception.IllegalPositionException;
import com.ghorbel.asma.blablacartest.lawn.MowerMover;
import com.ghorbel.asma.blablacartest.util.InputFileParser;

import java.io.IOException;
import java.util.List;

public class LawnMowing {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("One argument should be introduced with path to input file");
            System.exit(1);
        }
        try {
            InputFileParser fileParser = new InputFileParser(args[0]);
            List<MowerMover> mowerMoverList = fileParser.getMowerMoverList();
            for(MowerMover mowerMover : mowerMoverList) {
                Thread thread = new Thread(mowerMover);
                thread.start();
                thread.join();
            }
            for(MowerMover mowerMover : mowerMoverList) {
                System.out.println(mowerMover.getMower().toString());
            }
        } catch (IllegalPositionException | FileParserException | IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
