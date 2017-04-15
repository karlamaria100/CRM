package View;

import javax.swing.table.DefaultTableModel;

/**
 * Created by rares on 14-Apr-17.
 */
public class ProductsTableModel extends DefaultTableModel {

    public ProductsTableModel(int tableData, int colNames) {
        super(tableData, colNames);
    }


    public boolean isCellEditable(int row, int column) {
        if(column == 4 || column == 5)
            return true;
        return false;
    }


}
