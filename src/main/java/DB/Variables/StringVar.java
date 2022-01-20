package DB.Variables;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StringVar extends Variable{
    String type = "String";
    String name;

    public StringVar(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
