package by.iba;

import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.entity.car.Car;
import by.iba.entity.photo.Photo;

public interface PhotoService {

    Photo save(String imageUrl);

    Photo save(String imageUrl, Car car);

    void deleteAllByCarId(Long id);

    PhotoForAmazonResp findByUserId(Long id);

}
