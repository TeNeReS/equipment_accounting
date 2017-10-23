package arkhipov.equipment.ui;

import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JTable table;
    private JTextField nameFilter;
    private JComboBox<String> conditionFilter;
    private TableRowSorter<EquipmentTableModel> sorter;

    public MainFrame(EquipmentTableModel model) {
        setTitle("Учет оборудования");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.PAGE_AXIS));

        JButton editButton = new JButton("Редактировать прибор");
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;
            EditForm editForm = new EditForm(model, table, selectedRow);
            editForm.setVisible(true);
        });

        JButton addButton = new JButton("Добавить прибор");
        addButton.addActionListener(e -> {
            AddForm addForm = new AddForm(model);
            addForm.setVisible(true);
        });

        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener (e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1)
                return;
            int serialNumber = (int) table.getValueAt(selectedRow, 0);
            model.removeRow(selectedRow, serialNumber);
        });

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        List <RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        nameFilter = new JTextField(1);
        nameFilter.setMaximumSize(new Dimension(500, 25));
        JLabel nameFilterLabel = new JLabel("Фильтровать по названию:");
        nameFilter.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        nameFilterLabel.setLabelFor(nameFilter);

        conditionFilter = new JComboBox<>(new String[]{"", TechnicalCondition.WORKABLE.toString(), TechnicalCondition.FAULTY.toString(), TechnicalCondition.SERVICEABLE.toString()});
        conditionFilter.setMaximumSize(new Dimension(500, 25));
        JLabel conditionFilterLabel = new JLabel("Фильтровать по тех. сост.:");
        conditionFilter.addActionListener(e -> newFilter());
        conditionFilterLabel.setLabelFor(nameFilter);

        tablePanel.add(pane);

        toolPanel.add(nameFilterLabel);
        toolPanel.add(nameFilter);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        toolPanel.add(conditionFilterLabel);
        toolPanel.add(conditionFilter);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        toolPanel.add(editButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        toolPanel.add(addButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        toolPanel.add(deleteButton);

        mainPanel.add(tablePanel);
        mainPanel.add(toolPanel);

        this.add(mainPanel);
    }

    private void newFilter() {
        List<RowFilter<EquipmentTableModel, Object>> rf;
        try {
            rf = new ArrayList<>();
            rf.add(RowFilter.regexFilter(nameFilter.getText(), 2));
            rf.add(RowFilter.regexFilter((String) conditionFilter.getSelectedItem(), 5));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        RowFilter<EquipmentTableModel, Object> serviceFilter = RowFilter.andFilter(rf);
        sorter.setRowFilter(serviceFilter);
    }
}