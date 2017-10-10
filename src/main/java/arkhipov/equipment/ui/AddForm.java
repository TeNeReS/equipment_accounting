package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import java.text.ParseException;

import static arkhipov.equipment.EquipmentAccounting.DATE_FORMAT;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class AddForm extends JDialog {
    public AddForm(EquipmentTableModel model) {
        setTitle("Добавить прибор");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JTextField serialNumber = new JTextField(1);
        JTextField inventoryNumber = new JTextField(1);
        JTextField name = new JTextField(1);
        JTextField lastVerificationDate = new JTextField(1);
        JTextField responsiblePerson = new JTextField(1);
        JComboBox<String> currentCondition = new JComboBox<>(new String[]{TechnicalCondition.WORKABLE.toString(), TechnicalCondition.FAULTY.toString(), TechnicalCondition.SERVICEABLE.toString()});
        JTextField note = new JTextField(1);


        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener (arg0 -> {
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
        mainPanel.add(serialNumber);
        mainPanel.add(inventoryNumber);
        mainPanel.add(name);
        mainPanel.add(lastVerificationDate);
        mainPanel.add(responsiblePerson);
        mainPanel.add(currentCondition);
        mainPanel.add(note);
        mainPanel.add(saveButton);

        this.add(mainPanel);
    }
}
