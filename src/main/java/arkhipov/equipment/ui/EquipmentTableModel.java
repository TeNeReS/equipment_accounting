package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.repository.ApplianceRepository;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static arkhipov.equipment.EquipmentAccounting.DATE_FORMAT;

public class EquipmentTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {
            "Серийный номер", "Инвентарный номер", "Название", "Дата последней проверки",
            "Ответственный за прибор", "Техническое состояние", "Примечание"
    };

    private ApplianceRepository applianceRepository;

    private ArrayList<Appliance> appliances;

    public EquipmentTableModel(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
        this.appliances = (ArrayList<Appliance>) applianceRepository.findAll();
    }

    @Override
    public int getRowCount() {
        return appliances.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int column)
    {
        switch (column)
        {
            case 0: return Integer.class;
            case 1: return Integer.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        Appliance appliance = appliances.get(row);
        switch (column)
        {
            case 0: return appliance.getSerialNumber();
            case 1: return appliance.getInventoryNumber();
            case 2: return appliance.getName();
            case 3: return DATE_FORMAT.format(appliance.getLastVerificationDate());
            case 4: return appliance.getResponsiblePerson();
            case 5: return appliance.getCurrentCondition();
            case 6: return appliance.getNote();
            default: return null;
        }
    }

    public void removeRow(int row, int serialNumber) {
        int index = searchApplianceIndex(serialNumber);
        applianceRepository.delete(appliances.get(index));
        appliances.remove(index);
        fireTableDataChanged();
    }

    public void addRow(Appliance appliance) {
        applianceRepository.save(appliance);
        appliances.add(appliance);
        fireTableDataChanged();
    }

    public void editRow(Appliance appliance) {
        int index = searchApplianceIndex(appliance.getSerialNumber());
        applianceRepository.save(appliance);
        appliances.set(index, appliance);
        fireTableDataChanged();
    }

    private int searchApplianceIndex(int serialNumber) {
        Appliance appliance = appliances.stream().filter(a -> a.getSerialNumber() == serialNumber).findFirst().orElse(null);
        return appliances.indexOf(appliance);
    }
}