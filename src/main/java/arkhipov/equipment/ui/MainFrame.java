package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;

import javax.swing.*;

public class MainFrame extends JFrame {
    private EquipmentTableModel model;
    public void init(EquipmentTableModel model) {
        setTitle("Учет оборудования");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.model = model;

        JTable jTable = new JTable(model);

        JScrollPane pane = new JScrollPane(jTable);

        this.add(pane);
    }
}
