package arkhipov.equipment.ui;

import arkhipov.equipment.model.TechnicalCondition;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private EquipmentTableModel model;
    private JTable table;
    private JTextField nameFilter;
    private JComboBox<String> conditionFilter;
    private TableRowSorter<EquipmentTableModel> sorter;

    public MainFrame(EquipmentTableModel model) {
        setTitle("Учет оборудования");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.model = model;
        table = new JTable(model);

        JScrollPane pane = new JScrollPane(table);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.PAGE_AXIS));

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.PAGE_AXIS));

        JButton addButton = new JButton("Добавить прибор");
        addButton.addActionListener(e -> {
            AddForm addForm = new AddForm(model);
            addForm.setVisible(true);
        });

        JButton deleteButton = new JButton("Удалить");
        deleteButton.addActionListener (arg0 -> {
            try {
                int selectedPlanet = table.getSelectedRow();
                model.removeRow(selectedPlanet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        List <RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        nameFilter = new JTextField(1);
        nameFilter.setMaximumSize(new Dimension(500, 20));
        JLabel l1 = new JLabel("Фильтровать по названию:", SwingConstants.TRAILING);
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
        l1.setLabelFor(nameFilter);

        conditionFilter = new JComboBox<>(new String[]{"", TechnicalCondition.WORKABLE.toString(), TechnicalCondition.FAULTY.toString(), TechnicalCondition.SERVICEABLE.toString()});
        conditionFilter.setMaximumSize(new Dimension(500, 20));
        JLabel l2 = new JLabel("Фильтровать по тех. сост.:", SwingConstants.TRAILING);
        l2.setLabelFor(nameFilter);

        tablePanel.add(pane);

        toolPanel.add(addButton);
        toolPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        toolPanel.add(deleteButton);
        toolPanel.add(l1);
        toolPanel.add(nameFilter);
        toolPanel.add(l2);
        toolPanel.add(conditionFilter);

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
        RowFilter<EquipmentTableModel,Object> serviceFilter = RowFilter.andFilter(rf);
        sorter.setRowFilter(serviceFilter);
    }
}
