package pl.paciorek.dawid.managingSpaceTouristFlights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.forms.AddUserItemForm;
import pl.paciorek.dawid.managingSpaceTouristFlights.repository.FlightRepository;
import pl.paciorek.dawid.managingSpaceTouristFlights.repository.UserRepository;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.FlightService;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {
        if(bindResult.hasErrors())
            return "registerForm";
        else {
            userService.addWithDefaultRole(user);
            return "registerSuccess";
        }
    }

    @RequestMapping("/search")
    public ModelAndView getTourist() {
        ModelAndView modelAndView = new ModelAndView("search");
        List<User> userList = userService.listAll();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping("/edit")
    public ModelAndView editUserForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit_tourist");
        User user = userService.get(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping("/delete")
    public String deleteTourist(@RequestParam long id) {
        userService.delete(id);

        return "redirect:/";
    }



    @RequestMapping(value = "add-item/{flightId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable Long flightId) {
        User user = userRepository.findById(flightId).get();
        AddUserItemForm form = new AddUserItemForm(
                flightRepository.findAll(),
                user
        );

        List<Flight> flightss = flightService.listAll();
        model.addAttribute("title", "Add item to menu: " + user.getFirstName());
        model.addAttribute("form", form);
        model.addAttribute("forms", "Forms " + form.toString());
        model.addAttribute("flightss", flightss);
        model.addAttribute("user", user);
        return "add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddUserItemForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "index";
        }
        Flight theFlight = flightRepository.findById(form.getFlightId()).get();
        User theUser = userRepository.findById(form.getUserId()).get();
        theUser.addFlight(theFlight);
        userRepository.save(theUser);

        return "index";
    }

}