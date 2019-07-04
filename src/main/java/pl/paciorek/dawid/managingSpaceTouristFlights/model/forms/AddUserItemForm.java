package pl.paciorek.dawid.managingSpaceTouristFlights.model.forms;

import org.springframework.context.annotation.Primary;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class AddUserItemForm {
    @NotNull
    private Long userId;

    @NotNull
    private Long flightId;

    private Iterable<Flight> flights;

    private User user;

    public AddUserItemForm() {}

    public AddUserItemForm(Iterable<Flight> flights, User user) {
        this.flights = flights;
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Iterable<Flight> getFlights() {
        return flights;
    }

    public User getUser() {
        return user;
    }
}