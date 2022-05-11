package by.iba.impl;

import by.iba.PhotoService;
import by.iba.entity.car.Car;
import by.iba.entity.photo.Photo;
import by.iba.repository.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Photo save(String imageUrl) {
        Photo photo = new Photo();
        photo.setImageUrl(imageUrl);
        return photoRepository.save(photo);
    }

    @Override
    public Photo save(String imageUrl, Car car) {
        Photo photo = new Photo();
        photo.setImageUrl(imageUrl);
        photo.setCar(car);
        return photoRepository.save(photo);
    }

    @Override
    @Transactional
    public void deleteAllByCarId(Long id) {
        photoRepository.deleteAllByCarId(id);
    }

}
