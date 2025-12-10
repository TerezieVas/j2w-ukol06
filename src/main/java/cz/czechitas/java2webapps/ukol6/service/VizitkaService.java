package cz.czechitas.java2webapps.ukol6.service;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.repository.VizitkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VizitkaService {
    private VizitkaRepository vizitkaRepository;

    @Autowired
    public VizitkaService(VizitkaRepository vizitkaRepository) {
        this.vizitkaRepository = vizitkaRepository;
    }

    public Vizitka pridat(Vizitka vizitka) {
        vizitka.setId(null);
        vizitkaRepository.save(vizitka);
        return vizitka;
    }

    public Iterable<Vizitka> seznamVsech() {
        return vizitkaRepository.findAll();
    }

    public Optional<Vizitka> sezenId (int id) {
        return vizitkaRepository.getById(id);
    }
}
