package by.iba.repository;

import by.iba.entity.departure.Departure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartureRepository extends JpaRepository<Departure, Long> {

}
