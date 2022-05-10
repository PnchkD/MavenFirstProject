package by.iba;

import by.iba.entity.car.Car;
import by.iba.entity.photo.Photo;

public interface PhotoService {

    Photo save(String imageUrl);

    Photo save(String imageUrl, Car car);

}
