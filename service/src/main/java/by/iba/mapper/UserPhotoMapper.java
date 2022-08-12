package by.iba.mapper;


import by.iba.dto.resp.PhotoForAmazonResp;
import by.iba.entity.photo.UserPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserPhotoMapper {

    public PhotoForAmazonResp fillInDTO(UserPhoto userPhoto) {
        PhotoForAmazonResp photoForAmazonResp = new PhotoForAmazonResp();

        if (Objects.nonNull(userPhoto.getUserId())) {
            photoForAmazonResp.setUserId(userPhoto.getUserId());
        }

        if (Objects.nonNull(userPhoto.getUniqueId())) {
            photoForAmazonResp.setUniqueId(userPhoto.getUniqueId());
        }

        if (Objects.nonNull(userPhoto.getName())) {
            photoForAmazonResp.setName(userPhoto.getName());
        }

        if (Objects.nonNull(userPhoto.getPath())) {
            photoForAmazonResp.setPath(userPhoto.getPath());
        }

        if (Objects.nonNull(userPhoto.getSize())) {
            photoForAmazonResp.setSize(userPhoto.getSize());
        }

        if (Objects.nonNull(userPhoto.getExternalKey())) {
            photoForAmazonResp.setExternalKey(userPhoto.getExternalKey());
        }

        if (Objects.nonNull(userPhoto.getStatus())) {
            photoForAmazonResp.setStatus(userPhoto.getStatus());
        }

        return photoForAmazonResp;
    }

}
