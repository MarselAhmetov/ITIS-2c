package springContextContainer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springContextContainer.model.NameEntity;
import springContextContainer.repositories.NameEntityRepository;

import java.util.List;

@Component
public class NameContainer {

    @Autowired
    NameEntityRepository nameEntityRepository;

    public void add(NameEntity entity) {
        nameEntityRepository.save(entity);
    }

    public List<NameEntity> out() {
        return (List<NameEntity>) nameEntityRepository.findAll();
    }
}
