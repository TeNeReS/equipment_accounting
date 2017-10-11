package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import java.awt.*;
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
        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

        JTextField serialNumber = new JTextField(1);
        serialNumber.setMaximumSize(new Dimension(500, 20));
        JLabel serialNumberLabel = new JLabel("Серийный номер:");

        JTextField inventoryNumber = new JTextField(1);
        inventoryNumber.setMaximumSize(new Dimension(500, 20));
        JLabel inventoryNumberLabel = new JLabel("Инвентарный номер:");

        JTextField name = new JTextField(1);
        name.setMaximumSize(new Dimension(500, 20));
        JLabel nameLabel = new JLabel("Название:");

        JTextField lastVerificationDate = new JTextField(1);
        lastVerificationDate.setMaximumSize(new Dimension(500, 20));
        JLabel lastVerificationDateLabel = new JLabel("Дата последней поверки:");

        JTextField responsiblePerson = new JTextField(1);
        responsiblePerson.setMaximumSize(new Dimension(500, 20));
        JLabel responsiblePersonLabel = new JLabel("Ответственный за прибор:");

        JComboBox<String> currentCondition = new JComboBox<>(new String[]{TechnicalCondition.WORKABLE.toString(), TechnicalCondition.FAULTY.toString(), TechnicalCondition.SERVICEABLE.toString()});
        currentCondition.setMaximumSize(new Dimension(500, 20));
        JLabel currentConditionLabel = new JLabel("Техническое состояние:");

        JTextField note = new JTextField(1);
        note.setMaximumSize(new Dimension(500, 20));
        JLabel noteLabel = new JLabel("Примечание:");

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
        mainPanel.add(serialNumberLabel);
        mainPanel.add(serialNumber);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(inventoryNumberLabel);
        mainPanel.add(inventoryNumber);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(nameLabel);
        mainPanel.add(name);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(lastVerificationDateLabel);
        mainPanel.add(lastVerificationDate);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(responsiblePersonLabel);
        mainPanel.add(responsiblePerson);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(currentConditionLabel);
        mainPanel.add(currentCondition);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(noteLabel);
        mainPanel.add(note);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(saveButton);

        this.add(mainPanel);
    }
}
