package by.iba.repository;

import by.iba.entity.car.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    Optional<CarBrand> findCarBrandByName(String name);

}
