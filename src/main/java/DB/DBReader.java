package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public void updateByPlace() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            String line;
            //maybe add an if statement that checks if the number the func gets equals the rows counter and than just delete the
            //last row

            while ((line = br.readLine()) != null) {
            }


        }
    }


    public int getRows() throws IOException {
        return rowsCounter();
    }

    public int getColsCounter() throws IOException {
        return colsCounter();
    }
}
