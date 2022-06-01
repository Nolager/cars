package cl.andres.models.repository;

import cl.andres.models.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {

}
