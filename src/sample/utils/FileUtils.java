/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VICTUS
 */


    // Writes a line to the file, appending to the end of the file
   import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    // Static method to write a single line to the file
    public static void writeFile(String pathFile, String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathFile, true))) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException ex) {
            System.out.println("Error writing to file: " + ex.getMessage());
        }
    }

    // Static method to read all lines from the file and return them as a list of strings
    public static List<String> readFile(String pathFile) {
        List<String> listLine = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listLine.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error reading file (File not found): " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return listLine;
    }

    // Static method to overwrite the entire file with the provided list of lines
    public static void overwriteFile(String pathFile, List<String> lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathFile))) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error overwriting file: " + ex.getMessage());
        }
    }

}


