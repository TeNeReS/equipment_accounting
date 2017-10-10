package arkhipov.equipment;

import arkhipov.equipment.repository.ApplianceRepository;
import arkhipov.equipment.ui.EquipmentTableModel;
import arkhipov.equipment.ui.MainFrame;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.text.SimpleDateFormat;
/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class EquipmentAccounting {
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring.xml");
        context.refresh();

        ApplianceRepository repository = context.getBean(ApplianceRepository.class);
        EquipmentTableModel model = new EquipmentTableModel(repository);
        MainFrame mainFrame = new MainFrame(model);
        mainFrame.setVisible(true);
    }
}
