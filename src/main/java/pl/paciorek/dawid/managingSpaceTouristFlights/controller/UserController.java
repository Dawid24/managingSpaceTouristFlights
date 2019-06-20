package pl.paciorek.dawid.managingSpaceTouristFlights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

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

    @RequestMapping("edit")
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

}