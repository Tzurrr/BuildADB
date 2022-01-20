package DB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CCSVDB {
    private Scanner sc = new Scanner(System.in);
    private FileWriter writer;// = new FileWriter("filename.csv", true);
    private DBReader reader;
    private String path;
    private File file;

    public CCSVDB(String dbname) throws IOException {
        this.writer = new FileWriter(dbname+".csv", true);
        this.reader = new DBReader(dbname+".csv");
        this.path = dbname;
        this.file = new File(dbname+".csv");
    }

    public void Initializer() throws IOException {
        System.out.println("how many cols do you want");
        int opts = sc.nextInt();

        System.out.println("enter the cols names");
        //writer.append("1");
        writer.append("index");
        String a = this.sc.nextLine();
        this.writer.append(a);

        for (int i = 0; i < opts; i++) {
            a = this.sc.nextLine();
            this.writer.append(',');
            this.writer.append(a);
        }
        //writer.append('\n');
        writer.flush();
        //writer.close();
    }

    public void addARow() throws IOException {
        int cols = reader.getColsCounter();
        int rows = reader.getRows();

        this.writer.append('\n');
        this.writer.append(String.valueOf(rows));
        this.writer.append(',');

        //writer.append(String.valueOf(this.reader.getNumofRows()));
        System.out.println("enter values");

        String a = this.sc.nextLine();
        this.writer.append(a);

        //mosif amudot
        for (int i = 0; i < cols-1; i++) {
            this.writer.append(',');
            a = this.sc.nextLine();
            this.writer.append(a);
        }
        //writer.append('\n');
        writer.flush();
        //writer.close();
    }

    public void updateARow(int num_of_row) throws IOException {
        int place = 0;
        System.out.println("by which indentifier do you want to update the row?");

        ///
        //available: one of the col's variables (like "moshe"), index, place (look at Telegram to see the difference)

        String temp = sc.nextLine();
        int counter = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == "place".charAt(i)){
                counter++;
            }else {
                System.out.println("no such updating option");
                return;
            }
        }

        System.out.println("which col do you want to change");
        //available: one of the col's variables (like "moshe"), index, place (look at Telegram to see the difference)
        String col_to_change = sc.nextLine();
        System.out.println("to?");
        String what_to_change = sc.nextLine();

        reader.updateByPlace(what_to_change, num_of_row, col_to_change);
        writer.flush();
    }

    public void deleteLine(int num_of_row) throws IOException {
        //maybe I can give the user to delete a line by a variable in it.
        //for example, delete a line where ID = "123"
        reader.deleteLine(num_of_row, "");
        writer.flush();
    }

    public void deleteTable() throws IOException {
        writer.flush();
        writer.close();
        this.file.delete();
    }

    public void printTable() throws IOException {
        this.reader.print();
    }
}
