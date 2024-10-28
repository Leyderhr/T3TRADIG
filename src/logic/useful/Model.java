package logic.useful;

public class Model {
    private int id, supId, level;
    private String line;

    public Model(int id, int supId, int level, String line) {
        setId(id);
        setSupId(supId);
        setLevel(level);
        setLine(line);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        if(line != null && !(line.replaceAll(" ","").equalsIgnoreCase("")))
            this.line = line;
        else
            throw new IllegalArgumentException("Los valores introducidos no son admisibles");
    }
}
