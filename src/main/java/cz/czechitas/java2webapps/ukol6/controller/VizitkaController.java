package cz.czechitas.java2webapps.ukol6.controller;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.service.VizitkaService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VizitkaController {

    private VizitkaService vizitkaService;
/*podle zadání by tady měla být vizitkaRepository jako param konstruktoru,
ale já jsem oproti zadání udělala metodu repository v service a tady se mi to zobrazuje jako
seznamVsech */

    public VizitkaController(VizitkaService vizitkaService) {
        this.vizitkaService = vizitkaService;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public ModelAndView seznam() {
        return new ModelAndView("seznam")
                .addObject("seznam", vizitkaService.seznamVsech());
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable int id) {
        ModelAndView result = new ModelAndView("vizitka");
        Optional<Vizitka> maybeVizitka = vizitkaService.sezenId(id);
        if (maybeVizitka.isPresent()) {
            Vizitka vizitka = maybeVizitka.get();
            result.addObject("vizitka", vizitka);
            return result;
        } else {
            return result;
            //ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nova")
    public ModelAndView nova() {
        ModelAndView result = new ModelAndView("formular");
        return result;
    }

}
