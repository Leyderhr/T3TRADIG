package logic;

import java.io.Serializable;

public class Model implements Serializable{
    protected String line;

    public Model(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }
    public void setName(String line) {
        this.line = line;
    }
}