package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import java.text.ParseException;

import static arkhipov.equipment.EquipmentAccounting.DATE_FORMAT;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class EditForm extends Form {

    public EditForm(EquipmentTableModel model, JTable table, int selectedRow) {
        super(model);
        setTitle("Редактирование прибора");
        serialNumber.setText(String.valueOf(table.getValueAt(selectedRow, 0)));
        serialNumber.setEditable(false);

        inventoryNumber.setText(String.valueOf(table.getValueAt(selectedRow, 1)));

        name.setText(String.valueOf(table.getValueAt(selectedRow, 2)));
        name.setEditable(false);

        lastVerificationDate.setText(String.valueOf(table.getValueAt(selectedRow, 3)));

        responsiblePerson.setText(String.valueOf(table.getValueAt(selectedRow, 4)));

        currentCondition.setSelectedItem(String.valueOf(table.getValueAt(selectedRow, 5)));

        note.setText(String.valueOf(table.getValueAt(selectedRow, 6)));

        saveButton.addActionListener (arg0 -> {
            try {
                Appliance newAppliance = new Appliance(Integer.parseInt(serialNumber.getText()),
                        Integer.parseInt(inventoryNumber.getText()),
                        name.getText(),
                        DATE_FORMAT.parse(lastVerificationDate.getText()),
                        responsiblePerson.getText(),
                        TechnicalCondition.valueOf((String) currentCondition.getSelectedItem()),
                        note.getText());
                model.editRow(newAppliance);
                this.dispose();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}