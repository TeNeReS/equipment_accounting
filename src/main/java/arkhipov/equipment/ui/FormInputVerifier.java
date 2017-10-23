package arkhipov.equipment.ui;

import arkhipov.equipment.model.Appliance;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class FormInputVerifier extends InputVerifier {
    private final int NAME_MAX_LENGTH = 25;
    private EquipmentTableModel model;
    private String initialValue;

    public FormInputVerifier(EquipmentTableModel model) {
        this.model = model;
    }

    @Override
    public boolean verify(JComponent input) {
        String componentName = input.getName();
        String componentText = ((JTextField) input).getText();
        boolean result = true;
        switch (componentName) {
            case "serialNumber": {
                result = serialNumberVerify(componentText);
                break;
            }
            case "addInventoryNumber": {
                result = addInventoryNumberVerify(componentText);
                break;
            }
            case "editInventoryNumber": {
                result = editInventoryNumberVerify(componentText);
                break;
            }
            case "name": {
                result = nameVerify(componentText);
                break;
            }
            case "lastVerificationDate": {
                result = dateVerify(componentText);
                break;
            }
            case "responsiblePerson": {
                result = responsiblePersonVerify(componentText);
                break;
            }
        }
        if (!result)
            input.setBorder(new LineBorder(Color.RED));
        else
            input.setBorder(new LineBorder(Color.GREEN));
        return result;
    }

    private boolean patternVerify(String text, Pattern p) {
        Matcher m = p.matcher(text);
        return m.matches();
    }

    private boolean serialNumberVerify(String text) {
        Pattern p = Pattern.compile("\\d+");
        if (patternVerify(text, p)) {
            int serialNumber = Integer.parseInt(text);
            List<Appliance> applianceList = model.getAppliances();
            return applianceList.stream().filter(a -> a.getSerialNumber() == serialNumber).findFirst().orElse(null) == null;
        }
        return false;
    }

    private boolean addInventoryNumberVerify(String text) {
        Pattern p = Pattern.compile("\\d+");
        if (patternVerify(text, p)) {
            int inventoryNumber = Integer.parseInt(text);
            List<Appliance> applianceList = model.getAppliances();
            return applianceList.stream().filter(a -> a.getInventoryNumber() == inventoryNumber).findFirst().orElse(null) == null;
        }
        return false;
    }

    private boolean editInventoryNumberVerify(String text) {
        Pattern p = Pattern.compile("\\d+");
        if (patternVerify(text, p)) {
            int inventoryNumber = Integer.parseInt(text);
            int initialInventoryNumber = Integer.parseInt(initialValue);
            List<Appliance> applianceList = model.getAppliances();
            return applianceList.stream().filter(a -> a.getInventoryNumber() == inventoryNumber).findFirst().orElse(null) == null || initialInventoryNumber == inventoryNumber;
        }
        return false;
    }

    private boolean nameVerify(String text) {
        return text.length() <= NAME_MAX_LENGTH;
    }

    private boolean dateVerify(String text) {
        Pattern p = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[01])\\." +     // Не учитывается количество дней в каждом месяце
                "(0[1-9]|1[012])\\." +
                "[0-9]{4}");
        return patternVerify(text, p);
    }

    private boolean responsiblePersonVerify(String text) {
        Pattern p = Pattern.compile(".+");
        return patternVerify(text, p);
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }
}