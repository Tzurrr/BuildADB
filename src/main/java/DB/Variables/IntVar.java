package DB.Variables;

public class IntVar extends Variable{
    String type = "int";
    String name;

    public IntVar(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
