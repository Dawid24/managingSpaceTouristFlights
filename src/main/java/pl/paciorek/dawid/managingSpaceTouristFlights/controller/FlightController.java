package pl.paciorek.dawid.managingSpaceTouristFlights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.FlightService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FlightController {

    private FlightService flightService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/add_flight")
    public String add_spaceFlight(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight_form";
    }

    @PostMapping("/add_flight")
    public String addFlight(@ModelAttribute @Valid Flight flight,
                          BindingResult bindResult) {
        if(bindResult.hasErrors())
            return "flight_form";
        else {
            flightService.save(flight);
            return "index";
        }
    }

    @RequestMapping("/search_flight")
    public ModelAndView getFlights() {
        ModelAndView modelAndView = new ModelAndView("search_flight");
        List<Flight> flightsList = flightService.listAll();
        modelAndView.addObject("flightsList", flightsList);
        return modelAndView;
    }

    @RequestMapping("/edit_f")
    public ModelAndView editFlightForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit_flight");
        Flight flight = flightService.get(id);
        modelAndView.addObject("flight", flight);
        return modelAndView;
    }

    @RequestMapping("/delete_f")
    public String deleteFlight(@RequestParam long id) {
        flightService.delete(id);

        return "redirect:/";
    }
}
