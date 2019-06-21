package pl.paciorek.dawid.managingSpaceTouristFlights.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;
    private String arrival;
    private int seatsNumber;

    @ManyToMany(mappedBy = "flightList")
    private List<User> passengers = new ArrayList<>();

    private double price;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getArrival() {
        return arrival;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    public int getSeatsNumber() {
        return seatsNumber;
    }
    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
    public List<User> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", passengers=" + passengers +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Flight flight = (Flight) obj;
        return Objects.equals(id, flight.id) &&
                Objects.equals(departure, flight.departure) &&
                Objects.equals(arrival, flight.arrival) &&
                Objects.equals(seatsNumber, flight.seatsNumber) &&
                Objects.equals(passengers, flight.passengers) &&
                Objects.equals(price, flight.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departure, arrival, seatsNumber, passengers, price);
    }

    public void addTourist(User user) {
        passengers.add(user);
        user.getFlightList().add(this);
    }
}
