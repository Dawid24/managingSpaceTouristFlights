package pl.paciorek.dawid.managingSpaceTouristFlights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;
import pl.paciorek.dawid.managingSpaceTouristFlights.repository.FlightRepository;
import pl.paciorek.dawid.managingSpaceTouristFlights.repository.UserRepository;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.FlightService;
import pl.paciorek.dawid.managingSpaceTouristFlights.service.UserService;

@Service
public class OrderFlightController {

    private UserRepository userRepository;
    private FlightRepository flightRepository;
    private UserService userService;

    @Autowired
    FlightService flightService;

    public OrderFlightController(UserRepository userRepository, FlightRepository flightRepository, UserService userService) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.userService = userService;
    }

    @RequestMapping("/order")
    public ModelAndView orderFlightForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("order_flight");
        User user = userService.get(id);
        Flight flight = new Flight();
        modelAndView.addObject("user", user);
        modelAndView.addObject("flight", flight);
        return modelAndView;
    }

    @RequestMapping(value = "/ord", method = RequestMethod.POST)
    public String setRelational(@RequestParam(value = "user_id", required = false) Long user_id,
                                @RequestParam(value = "flight_id", required = false) Long flight_id,
                                @RequestParam(value = "seats", required = false) Long seats) {
        User user = userService.get(user_id);
        Flight flight = flightService.get(flight_id);
        if (flight.getSeatsNumber() > seats) {
            flight.setSeatsNumber((int) (flight.getSeatsNumber() - seats));
            user.addFlight(flight);
        }
        return "index";
    }
}