package util.table;


import logic.useful.Model;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableModel extends DefaultTableModel{

    public TableModel(){
        Object[] columnNames = {"No.", "Nombre"};
        this.setColumnIdentifiers(columnNames);
    }

    public TableModel(List<Model> list){
        Object[] columnNames = {"No.", "Nombre"};
        this.setColumnIdentifiers(columnNames);

        for(Model m: list){
            Object[] newRow = new Object[]{m.getId(), m.getLine()};
            addRow(newRow);
        }
    }

    public void actTable(List<Model> list){
        String[] columnNames = {"No.", "Nombre"};
        this.setColumnIdentifiers(columnNames);

        for (Model m: list) {
            Object[] newRow = new Object[]{m.getId(), m.getLine()};
            addRow(newRow);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    public void insert(Model m){
        Object[] newRow = new Object[]{m.getId(), m.getLine()};
        addRow(newRow);
    }

    public void delete(int pos){
        removeRow(pos);
    }
}

