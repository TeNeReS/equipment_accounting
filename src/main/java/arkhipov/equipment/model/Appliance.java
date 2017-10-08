package arkhipov.equipment.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
@Entity
@Table(name = "appliances")
public class Appliance {
    @Id
    @Column(name = "serial_number")
    private Integer serialNumber;

    @Column(name = "inventory_number")
    private int inventoryNumber;
    private String name;

    @Column(name = "last_verification_date")
    private Date lastVerificationDate;

    @Column(name = "responsible_person")
    private String responsiblePerson;

    @Column(name = "current_condition")
    @Enumerated(EnumType.STRING)
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

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastVerificationDate() {
        return lastVerificationDate;
    }

    public void setLastVerificationDate(Date lastVerificationDate) {
        this.lastVerificationDate = lastVerificationDate;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public TechnicalCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(TechnicalCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Appliance{" +
                "serialNumber=" + serialNumber +
                ", inventoryNumber=" + inventoryNumber +
                ", name=" + name +
                ", lastVerificationDate=" + lastVerificationDate +
                ", responsiblePerson=" + responsiblePerson +
                ", currentCondition=" + currentCondition +
                ", note=" + note +
                '}';
    }
}