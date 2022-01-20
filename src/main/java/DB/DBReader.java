package DB;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private String filename;
    private final String COMMA_DELIMITER = ",";
    private int rows_counter = 0;
    private int cols_counter = 0;

    public DBReader(String name){
        this.filename = name;
    }

    public void read() throws IOException {
        ArrayList<String> records = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
            }
        }
    }

    public int colsCounter() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line = br.readLine();
            String[] values = line.split(COMMA_DELIMITER);
            for (int i = 0; i < values.length; i++) {
                System.out.println(values[i]);
            }
            int cols = values.length-1;
            System.out.println(cols);
            return cols;
        }
    }

    public int rowsCounter() throws IOException {
        ArrayList<String> records = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            int rows = 0;

            while ((line = br.readLine()) != null) {
                rows++;
            }
            return rows;
        }
    }

    public void updateByPlace(String replace, int row, String col) throws IOException {
        int place = 0;
        int counter = 0;
        if (row == 0){
            System.out.println("no such line");
            return;
        }

        //File inputFile = new File(this.filename);
        //CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        ArrayList<String[]> csvBody = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                csvBody.add(values);
            }
        }

        //finding the place of the user requested col
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String[] line;
            line = br.readLine().split(COMMA_DELIMITER);
            for (int i = 0; i < line.length; i++) {
                for (int j = 0; j < line[i].length(); j++) {
                    if (col.length() == line[i].length()) {
                        if (line[i].charAt(j) == col.charAt(j)) {
                            //place = i;
                            counter++;
                        }
                    }
                }
                if(counter == line[i].length()){
                    place = i;
                    break;
                }
            }
        }

        if (place == 0){
            System.out.println("cant change the index");
            return;
        }
        // get CSV row column  and replace with by using row and column
        System.out.println("place: " + place);
        System.out.println("replace" + replace);
        csvBody.get(row)[place] = replace;
        FileWriter writer = new FileWriter(this.filename);
        for (int i = 0; i < csvBody.size(); i++) {
            for (int j = 0; j < csvBody.get(i).length; j++) {
                writer.append(csvBody.get(i)[j]);
                writer.append(',');
            }
            //if (i < csvBody.size() - 1){
                writer.append('\n');
            //}
        }
        //writer.append(csvBody.get(csvBody.size()-1)[csvBody.get(csvBody.size()-1).length-1]);
        // Write to CSV file which is open
        //CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
        //writer.writeAll(csvBody);
        writer.flush();
    }


    public void deleteLine(int lineNumber, String data) throws IOException {
        if (lineNumber == 0){
            System.out.println("no such line");
            return;
        }

        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }


    public int getRows() throws IOException {
        return rowsCounter();
    }

    public int getColsCounter() throws IOException {
        return colsCounter();
    }
}
