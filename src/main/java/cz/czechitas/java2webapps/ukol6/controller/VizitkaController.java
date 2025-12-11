package cz.czechitas.java2webapps.ukol6.controller;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.service.VizitkaService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id:[0-9]+}")
    public Object detail(@PathVariable int id) {
        Optional<Vizitka> vizitka = vizitkaService.sezenId(id);
        if (vizitka.isPresent()) {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitka.get());
        } else {
            return ResponseEntity.notFound().build();
        }
       /* if (vizitka.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitka);
        }*/


//moje původní varianta
      /*  if (maybeVizitka.isPresent()) {
            Vizitka vizitka = maybeVizitka.get();
            result.addObject("vizitka", vizitka);
            return result;
        } else {
            return result;
        //ResponseEntity.notFound().build();
*/
        //  ModelAndView result = new ModelAndView("vizitka");
        // 1. varianta Lenka
       /* Optional<Vizitka> vizitka = Optional.ofNullable(vizitkaService.sezenId(id));
        if (vizitka.isPresent()) {
            return new ModelAndView("vizitka")
                    .addObject("vizitka", vizitka);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @GetMapping("/nova")
    public ModelAndView nova() {
        ModelAndView result = new ModelAndView("formular");
        result.addObject("vizitka", new Vizitka());//bez tohoto to nejelo a hlásilo chybu v missing data_model(bind)
        return result;
    }

    @PostMapping("/nova")
    public String pridat(@ModelAttribute("vizitka") @Valid Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formular";
        }
        vizitkaService.pridat(vizitka);
        return "redirect:/";
    }
}
