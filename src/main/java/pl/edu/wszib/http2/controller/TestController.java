package pl.edu.wszib.http2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@Controller
public class TestController {

    public class ZmienObiekt {
        public String getZmien() {
            return zmien;
        }
        public void setZmien(String zmien) {
            this.zmien = zmien;
        }
        private String zmien;
    }
    private ZmienObiekt zmienObiekt;
    @GetMapping
    public  String test(Model model){
        model.addAttribute("imie", "Taras");
        String[] imiona = {"Adam", "Kasia", "Gerard"};
        model.addAttribute("imiona", imiona);
        model.addAttribute("zmienObiekt", new ZmienObiekt());
        return "test";
    }

    @PostMapping("/update")
    public String update(ZmienObiekt zmienObiekt, Model model){
        this.zmienObiekt = zmienObiekt;
        model.addAttribute("zmienObiekt",new ZmienObiekt());
        return "redirect:/test/nowy";
    }

    @GetMapping("/nowy")
    public  String nowy (Model model){
        model.addAttribute("zmienObiekt", this.zmienObiekt);
        return "nowy";
    }
}
