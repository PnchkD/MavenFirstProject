package by.iba.impl;

import by.iba.PhotoService;
import by.iba.config.BucketName;
import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.entity.car.Car;
import by.iba.entity.photo.Photo;
import by.iba.entity.photo.UserPhoto;
import by.iba.exception.ServiceException;
import by.iba.mapper.UserPhotoMapper;
import by.iba.repository.PhotoRepository;
import by.iba.repository.UserPhotoRepository;
import by.iba.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final UserPhotoRepository userPhotoRepository;
    private final UserPhotoMapper userPhotoMapper;
    private final S3Service s3Service;

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

    @Override
    @Transactional
    public PhotoForAmazonResp findByUserId(Long id) {
        List<UserPhoto> userPhoto = userPhotoRepository.findAllByUserId(id);

        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();

        //        Adding 10 minutes(1000 msc == 1 sec, 60 sec == 1 min)
        expTimeMillis += 1000 * 60 * 10;
        expiration.setTime(expTimeMillis);

        PhotoForAmazonResp photo = new PhotoForAmazonResp();

        if(!userPhoto.isEmpty()) {
            LocalDateTime userAvatarDate = userPhoto.stream()
                    .map(x -> x.getDateOfCreation())
                    .max(Comparator.naturalOrder())
                    .get();

            Optional<UserPhoto> userAvatar = userPhoto.stream()
                    .filter(x -> x.getDateOfCreation() == userAvatarDate)
                    .findFirst();

            if (userAvatar.isPresent()) {
                String presignedUrl = s3Service.getPresignedImageUrl(userAvatar.get().getPath(), userAvatar.get().getExternalKey(), expiration);
                photo = userPhotoMapper.fillInDTO(userPhoto.get(userPhoto.size() - 1));
                photo.setPresignedUrl(presignedUrl);
            }
        }

        return photo;
    }

}
