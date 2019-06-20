package pl.paciorek.dawid.managingSpaceTouristFlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
}
