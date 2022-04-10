package pl.edu.wszib.http2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.http2.service.ToDoService;
import pl.edu.wszib.http2.service.exception.NotFoundException;
import pl.edu.wszib.http2.service.model.Produkt;
import pl.edu.wszib.http2.service.model.ToDo;
import pl.edu.wszib.http2.service.model.ToDoStatus;

@Controller
@RequestMapping("/ToDo")
public class ToDotController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping
    public String defaultView() {
        return "redirect:/ToDo/list";
    }

    @GetMapping("/list")
    public String list(Model model) {

        ToDo toDo = new ToDo();
        toDo.setZadanie("Zrób ToDoka");
        toDo.setStatus(ToDoStatus.NEW);
        toDo.setTermin("Wystarczający");
        toDoService.create(toDo);

        model.addAttribute("ToDosiki", toDoService.list());
        return "ToDo/list-ToDo";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", new Produkt());
        return "produkt/create-produkt";
    }

    @PostMapping("/create")
    public String createAction(ToDo toDo, Model model) {
        toDoService.create(toDo);
        return "redirect:/produkt/list";
    }
    @GetMapping("/get")
    public String getProdukt(@RequestParam Integer id, Model model) {
        model.addAttribute("produkt", toDoService.get(id));
        return "produkt/get-produkt";
    }
    @GetMapping("/update")
    public String updateProdukt(@RequestParam Integer id, Model model) {
        model.addAttribute("updateProdukt", toDoService.get(id));
        return "produkt/update-produkt";
    }
    @PostMapping("/update")
    public String updateActionAfterUpdate(ToDo toDo, Model model) {
        toDoService.update(toDo);
        return "redirect:/produkt/get?id=" + toDo.getId();
    }
    @GetMapping("/delete")
    public String deleteProfileView(@RequestParam Integer id, Model model) {
        model.addAttribute("produkt", toDoService.get(id));
        return "produkt/delete-produkt";
    }
    @PostMapping("/delete")
    public String deleteProduktAction(@RequestParam Integer id, Model model) {
        toDoService.delete(id);
        return "redirect:/produkt/list";
    }
    @ExceptionHandler(NotFoundException.class)
    public String notFoundView() {
        return "produkt/404-produkt";
    }
}
