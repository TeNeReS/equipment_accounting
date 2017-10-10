package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.model.TechnicalCondition;
import arkhipov.equipment.repository.ApplianceRepository;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
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
    public boolean isCellEditable(int row, int column)
    {
        switch (column)
        {
            case 0: return false;
            case 2: return false;
            default: return true;
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

    @Override
    public void setValueAt(Object value, int row, int column)
    {
        Appliance appliance = appliances.get(row);
        switch (column)
        {
            case 1: appliance.setInventoryNumber((Integer) value); break;
            case 3:
                try {
                    appliance.setLastVerificationDate(DATE_FORMAT.parse((String) value));
                } catch (ParseException e) {
                    e.printStackTrace();
                } break;
            case 4: appliance.setResponsiblePerson((String) value); break;
            case 5: appliance.setCurrentCondition(TechnicalCondition.valueOf((String) value)); break;
            case 6: appliance.setNote((String) value); break;
        }
        applianceRepository.save(appliance);
        appliances.set(row, appliance);

        fireTableCellUpdated(row, column);
    }

    public void removeRow(int row) {
        applianceRepository.delete(appliances.get(row));
        appliances.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void addRow(Appliance appliance) {
        applianceRepository.save(appliance);
        appliances.add(appliance);
        fireTableDataChanged();
    }
}