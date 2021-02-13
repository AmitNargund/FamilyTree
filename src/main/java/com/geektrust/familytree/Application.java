package com.geektrust.familytree;

import com.geektrust.familytree.model.Family;
import com.geektrust.familytree.util.Constants;
import com.geektrust.familytree.util.FamilyInitializer;

import java.io.*;

public class Application {

    public static void main(String[] args) {
        try {
            if( args.length == 0) {
                throw new IllegalArgumentException("Please provide the path to input file");
            }

            String inputFilePath = args[0];
            File inputFile = new File(inputFilePath);

            if (inputFile.exists()) {
                Family family = FamilyInitializer.initializeFamily();
                processInputFile(args[0], family);
            } else {
                throw new FileNotFoundException("Input file is not available at location: " + inputFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processInputFile(String inputFilePath, Family family) throws FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String command = null;
            String result = null;
            while (null != (command = reader.readLine())) {
                String[] commands = command.split(" ");

                switch (commands[0]) {
                    case Constants.ADD_CHILD:
                        result = family.addChild(commands[1], commands[2], commands[3]);
                        break;
                    case Constants.GET_RELATIONSHIP:
                        result = family.getRelationShip(commands[1], commands[2]);
                        break;
                    default:
                        result = Constants.INVALID_COMMAND;
                        break;
                }

                System.out.println(result);
            }
        } catch (IOException e) {
            System.out.println("Error while reading input file at path: " + inputFilePath);
        }

    }
}
