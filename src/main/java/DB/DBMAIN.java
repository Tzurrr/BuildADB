package DB;

import java.io.IOException;

public class DBMAIN {
    public static void main(String[] args) throws IOException {
        CCSVDB a = new CCSVDB("ggg");
        CCSVDB new1 = new CCSVDB("aaa");
        new1.Initializer();
        new1.addARow();
        //a.Initializer();
        //a.addARow();
        //a.addARow();
        //a.deleteTable();
        //a.deleteLine(1);
        //a.updateARow(1);
        //a.printTable();
    }
}
