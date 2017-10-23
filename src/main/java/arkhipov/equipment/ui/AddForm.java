package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import java.text.ParseException;

import static arkhipov.equipment.EquipmentAccounting.DATE_FORMAT;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class AddForm extends Form {

    public AddForm(EquipmentTableModel model) {
        super(model);
        setTitle("Добавление прибора");
        saveButton.addActionListener (arg0 -> {
            for (int i = 0; i < mainPanel.getComponentCount(); i++) {
                JComponent component = (JComponent) mainPanel.getComponent(i);
                if (component.getInputVerifier() != null && !component.getInputVerifier().verify(component))
                    return;
            }
            try {
                Appliance newAppliance = new Appliance(Integer.parseInt(serialNumber.getText()),
                        Integer.parseInt(inventoryNumber.getText()),
                        name.getText(),
                        DATE_FORMAT.parse(lastVerificationDate.getText()),
                        responsiblePerson.getText(),
                        TechnicalCondition.valueOf((String) currentCondition.getSelectedItem()),
                        note.getText());
                model.addRow(newAppliance);
                this.dispose();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}