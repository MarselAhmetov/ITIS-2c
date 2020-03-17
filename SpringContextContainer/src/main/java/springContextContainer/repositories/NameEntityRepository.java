package springContextContainer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springContextContainer.model.NameEntity;

@Repository
public interface NameEntityRepository extends CrudRepository<NameEntity, Long> {
}
