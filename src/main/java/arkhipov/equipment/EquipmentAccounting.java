package arkhipov.equipment;

import arkhipov.equipment.repository.ApplianceRepository;
import arkhipov.equipment.ui.EquipmentTableModel;
import arkhipov.equipment.ui.MainFrame;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public class EquipmentAccounting {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:spring.xml"); //move from src.main.java to src.main.resources
        context.refresh();

        ApplianceRepository repository = context.getBean(ApplianceRepository.class);
        EquipmentTableModel model = new EquipmentTableModel(repository);
        MainFrame mainFrame = new MainFrame();

        mainFrame.init(model);
        mainFrame.setVisible(true);
    }
}
