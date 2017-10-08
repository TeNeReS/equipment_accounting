package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;
import arkhipov.equipment.repository.ApplianceRepository;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

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
    public Object getValueAt(int row, int column)
    {
        Appliance appliance = appliances.get(row);

        switch (column)
        {
            case 0: return appliance.getSerialNumber();
            case 1: return appliance.getInventoryNumber();
            case 2: return appliance.getName();
            case 3: return appliance.getLastVerificationDate();
            case 4: return appliance.getResponsiblePerson();
            case 5: return appliance.getCurrentCondition();
            case 6: return appliance.getNote();
            default: return null;
        }
    }
}