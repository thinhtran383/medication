package com.example.medicine.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableHelper {
    public static void setTableReadOnly(JTable table) {
        TableModel oldModel = table.getModel();
        int columnCount = oldModel.getColumnCount();
        Object[] columnNames = new Object[columnCount];

        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = oldModel.getColumnName(i);
        }

        DefaultTableModel newModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int rowCount = oldModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object[] rowData = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
                rowData[j] = oldModel.getValueAt(i, j);
            }
            newModel.addRow(rowData);
        }

        table.setModel(newModel);
    }
}
