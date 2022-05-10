package by.iba.mapper;

import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.entity.car.CarBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BrandMapper {

    public BrandDTO fillInDTO(CarBrand brand) {
        BrandDTO brandDTO = new BrandDTO();

        if (Objects.nonNull(brand.getName())) {
            brandDTO.setName(brand.getName());
        }

        if(Objects.nonNull(brand.getId())) {
            brandDTO.setId(brand.getId());
        }

        if(Objects.nonNull(brand.getDateOfCreation())) {
            brandDTO.setDateOfCreation(brand.getDateOfCreation());
        }

        if(Objects.nonNull(brand.getDateOfLastUpdate())) {
            brandDTO.setDateOfLastUpdate(brand.getDateOfLastUpdate());
        }

        return brandDTO;
    }

    public CarBrand fillFromReq(BrandReqDTO brandReqDTO) {
        CarBrand brand = new CarBrand();

        if (Objects.nonNull(brandReqDTO.getName())) {
            brand.setName(brand.getName());
        }

        return brand;
    }
}
