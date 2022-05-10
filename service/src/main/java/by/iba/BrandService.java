package by.iba;

import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.entity.car.CarBrand;

import java.util.List;

public interface BrandService {

    CarBrand getByName(String name);

    List<BrandDTO> getAll();

    BrandDTO create(BrandReqDTO brandReqDTO);

    void delete(Long id);

}
