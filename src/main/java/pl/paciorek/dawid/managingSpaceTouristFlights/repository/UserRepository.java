package pl.paciorek.dawid.managingSpaceTouristFlights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.paciorek.dawid.managingSpaceTouristFlights.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
