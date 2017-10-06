package arkhipov.equipment.repository;

import arkhipov.equipment.model.Appliance;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vladimir Arkhipov, v.arkhipov.v@gmail.com.
 */
public interface ApplianceRepository extends CrudRepository<Appliance, Integer> {
}
