package cz.czechitas.java2webapps.ukol6.repository;


import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VizitkaRepository extends CrudRepository<Vizitka, Integer> {
    List<Vizitka> id(Integer id);

    Optional<Vizitka> getById(int id);//ted je tady tato metoda zbytečná. Použitá findById se nemusela vytvářet - je poděděná.
}
