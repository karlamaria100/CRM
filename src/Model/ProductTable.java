package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTable extends AbstractTableModel {

    public String[] columnNames = { "Nume Produs", "Stoc", "Pret" };

    Object[] row = {"null", "ceva", "null"};
    Object[][]data = { row };

    public void updateTable(ArrayList<Product> listProducts){
        data = new Object[listProducts.size()][3];
        for(int i = 0; i < listProducts.size(); i++){
            data[i][0] = listProducts.get(i).getName();
            data[i][2] = listProducts.get(i).getPrice();
            data[i][1] = listProducts.get(i).getQuantity();
        }
        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(data[i][j] + " ");
            }
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return columnNames.length;
    }

    @Override
    public int getColumnCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
