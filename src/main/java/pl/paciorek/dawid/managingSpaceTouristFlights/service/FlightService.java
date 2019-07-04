package pl.paciorek.dawid.managingSpaceTouristFlights.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;
import pl.paciorek.dawid.managingSpaceTouristFlights.repository.FlightRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void save(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> listAll() {
        return (List<Flight>) flightRepository.findAll();
    }

    public Flight get(Long id) {
        Optional<Flight> result = flightRepository.findById(id);
        return result.get();
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }

    //@Transactional
    //public void update(Flight flight) {
       // Flight find = flightRepository.findById()

    //}
}
