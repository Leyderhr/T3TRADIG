package util.table;


import logic.Model;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableTreeModel extends DefaultTableModel{

    public TableTreeModel(){
        String[] columnNames = {"Nombre"};
        this.setColumnIdentifiers(columnNames);
    }

    public TableTreeModel(List<Model> list){
        String[] columnNames = {"Nombre"};
        this.setColumnIdentifiers(columnNames);

        for(Model m: list){
            Object[] newRow = new Object[]{m.getLine()};
            addRow(newRow);
        }
    }

    public void actTable(List<Model> list){
        String[] columnNames = {"Nombre"};
        this.setColumnIdentifiers(columnNames);

        for (Model m: list) {
            Object[] newRow = new Object[]{m.getLine()};
            addRow(newRow);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    public void insert(String name){
        Object[] newRow = new Object[]{name};
        addRow(newRow);
    }

    public void delete(int pos){
        removeRow(pos);
    }
}

