package arkhipov.equipment.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
@Entity
@Table(name = "appliances")
public class Appliance {
    private Integer serialNumber;
    private int inventoryNumber;
    private String name;
    private Date lastVerificationDate;
    private String responsiblePerson;
    private TechnicalCondition currentCondition;
    private String note;

    public Appliance() {}

    public Appliance(Integer serialNumber, int inventoryNumber, String name, Date lastVerificationDate, String responsiblePerson, TechnicalCondition currentCondition, String note) {
        this.serialNumber = serialNumber;
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.lastVerificationDate = lastVerificationDate;
        this.responsiblePerson = responsiblePerson;
        this.currentCondition = currentCondition;
        this.note = note;
    }
}