package DB;

import DB.Variables.Variable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CCSVDB {
    private Scanner sc = new Scanner(System.in);
    private FileWriter writer;// = new FileWriter("filename.csv", true);
    private DBReader reader;
    private String name;
    private File file;
    private String path;

    private Variable temp;

    public CCSVDB(String dbname) throws IOException {
        this.writer = new FileWriter(dbname+".csv", true);
        this.reader = new DBReader(dbname+".csv");
        this.name = dbname;
        this.file = new File(dbname+".csv");
        this.path = dbname+".csv";
    }

    public void Initializer() throws IOException {
        writer.append("index");
        writer.append(',');

        System.out.println("how many cols do you want");
        int opts = sc.nextInt();
        //if (opts == 1){
        //writer.append(',');
        //}

        System.out.println("enter the col name");
        String a = this.sc.next();
        this.writer.append(a);

        System.out.println("enter the col type");
        System.out.println("a");
        String temp_type = this.sc.next();
        this.temp = new Variable(temp_type, this.name, a);

        for (int i = 0; i < opts-1; i++) {
            System.out.println("enter the col name");
            a = this.sc.next();
            this.writer.append(',');
            this.writer.append(a);

            System.out.println("enter the col type");
            temp_type = sc.next();
            this.temp = new Variable(temp_type, this.name, a);
        }
        //writer.append('\n');
        writer.flush();
        //writer.close();
    }

    public void addARow() throws IOException {
        int cols = reader.getColsCounter();
        int rows = reader.getRows();

        //BUG: appends only the last col created
        this.temp.makeMetadataFiles();

        this.writer.append('\n');
        this.writer.append(String.valueOf(rows));
        this.writer.append(',');

        //writer.append(String.valueOf(this.reader.getNumofRows()));
        System.out.println("enter values");

        String a = this.sc.next();
        this.writer.append(a);

        //mosif amudot
        for (int i = 0; i < cols-1; i++) {
            this.writer.append(',');
            a = this.sc.next();
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
        //reader.deleteLine(num_of_row, "");
        reader.deleteLine(num_of_row);
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
