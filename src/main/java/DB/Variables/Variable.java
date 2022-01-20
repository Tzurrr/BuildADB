package DB.Variables;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Variable {
    private String name;
    private Date date;
    private String string;
    private int anInt;
    private ArrayList<Variable> binder= new ArrayList<Variable>();
    private String path;

    public Variable(String type, String path, String name){
        if (type.length() == 3){
            IntVar a = new IntVar(name);
            this.binder.add(a);
        }

        if (type.length() == 4){
            DateVar a = new DateVar(name);
            this.binder.add(a);
        }

        if (type.length() == 6){
            StringVar a = new StringVar(name);
            this.binder.add(a);
        }

        this.path = path;
    }

    public void makeMetadataFiles() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(this.path+".json"), this.binder);
    }


    public Variable() {
    }
}
