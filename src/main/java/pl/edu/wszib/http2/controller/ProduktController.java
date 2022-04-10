package pl.edu.wszib.http2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.http2.service.ProduktService;
import pl.edu.wszib.http2.service.exception.NotFoundException;
import pl.edu.wszib.http2.service.model.Filtr;
import pl.edu.wszib.http2.service.model.Produkt;

@Controller
@RequestMapping("/produkt")
public class ProduktController {
    @Autowired
    private ProduktService produktService;

    @GetMapping
    public String defaultView() {
        return "redirect:/produkt/list";
    }

    @GetMapping("/list")
    public String list(Filtr filtr, Model model) {
        if(filtr == null){
            model.addAttribute("produkty", produktService.list());
            model.addAttribute("filtr", new Filtr());
        } else {
            model.addAttribute("produkty", produktService.list(filtr));
            model.addAttribute("filtr", filtr);
       }
/*
        Produkt produkt = new Produkt();
        produkt.setCena(4.5f);
        produkt.setIlosc(56);
        produkt.setNazwa("Domestos");
        produktService.create(produkt);
*/
         return "produkt/list-produkt";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", new Produkt());
        return "produkt/create-produkt";
    }

    @PostMapping("/create")
    public String createAction(Produkt produkt, Model model) {
        produktService.create(produkt);
        return "redirect:/produkt/list";
    }
    @GetMapping("/get")
    public String getProdukt(@RequestParam Integer id, Model model) {
        model.addAttribute("produkt", produktService.get(id));
        return "produkt/get-produkt";
    }
    @GetMapping("/update")
    public String updateProdukt(@RequestParam Integer id, Model model) {
        model.addAttribute("updateProdukt", produktService.get(id));
        return "produkt/update-produkt";
    }
    @PostMapping("/update")
    public String updateActionAfterUpdate(Produkt produkt, Model model) {
        produktService.update(produkt);
        return "redirect:/produkt/get?id=" + produkt.getId();
    }
    @GetMapping("/delete")
    public String deleteProfileView(@RequestParam Integer id, Model model) {
        model.addAttribute("produkt", produktService.get(id));
        return "produkt/delete-produkt";
    }
    @PostMapping("/delete")
    public String deleteProduktAction(@RequestParam Integer id, Model model) {
        produktService.delete(id);
        return "redirect:/produkt/list";
    }
    @ExceptionHandler(NotFoundException.class)
    public String notFoundView() {
        return "produkt/404-produkt";
    }
}
