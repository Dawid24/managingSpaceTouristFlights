package pl.paciorek.dawid.managingSpaceTouristFlights.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("greeting", "Welcome user!");
        return "index";
    }
}
