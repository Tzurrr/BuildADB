package DB.Variables;

import java.util.Date;

public class DateVar extends Variable{
    String type = "Date";
    String name;

    public DateVar(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
