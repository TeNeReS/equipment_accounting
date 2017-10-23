package arkhipov.equipment.ui;

import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class Form extends JDialog {
    JPanel mainPanel;
    JTextField serialNumber;
    JTextField inventoryNumber;
    JTextField name;
    JTextField lastVerificationDate;
    JTextField responsiblePerson;
    JComboBox<String> currentCondition;
    JTextField note;
    JButton saveButton;
    FormInputVerifier verifier;

    public Form(EquipmentTableModel model) {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setAlignmentX(CENTER_ALIGNMENT);

        verifier = new FormInputVerifier(model);

        serialNumber = new JTextField(1);
        serialNumber.setName("serialNumber");
        serialNumber.setMaximumSize(new Dimension(500, 20));
        serialNumber.setInputVerifier(verifier);
        JLabel serialNumberLabel = new JLabel("Серийный номер:");

        inventoryNumber = new JTextField(1);
        inventoryNumber.setName("inventoryNumber");
        inventoryNumber.setMaximumSize(new Dimension(500, 20));
        inventoryNumber.setInputVerifier(verifier);
        JLabel inventoryNumberLabel = new JLabel("Инвентарный номер:");

        name = new JTextField(1);
        name.setName("name");
        name.setMaximumSize(new Dimension(500, 20));
        name.setInputVerifier(verifier);
        JLabel nameLabel = new JLabel("Название:");

        lastVerificationDate = new JTextField(1);
        lastVerificationDate.setName("lastVerificationDate");
        lastVerificationDate.setMaximumSize(new Dimension(500, 20));
        lastVerificationDate.setInputVerifier(verifier);
        JLabel lastVerificationDateLabel = new JLabel("Дата последней поверки:");

        responsiblePerson = new JTextField(1);
        responsiblePerson.setName("responsiblePerson");
        responsiblePerson.setMaximumSize(new Dimension(500, 20));
        responsiblePerson.setInputVerifier(verifier);
        JLabel responsiblePersonLabel = new JLabel("Ответственный за прибор:");

        currentCondition = new JComboBox<>(new String[]{TechnicalCondition.WORKABLE.toString(), TechnicalCondition.FAULTY.toString(), TechnicalCondition.SERVICEABLE.toString()});
        currentCondition.setMaximumSize(new Dimension(500, 20));
        JLabel currentConditionLabel = new JLabel("Техническое состояние:");

        note = new JTextField(1);
        note.setMaximumSize(new Dimension(500, 20));
        JLabel noteLabel = new JLabel("Примечание:");

        saveButton = new JButton("Сохранить");

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