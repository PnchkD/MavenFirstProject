package by.iba.impl;

import by.iba.BrandController;
import by.iba.BrandService;
import by.iba.CarService;
import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.req.user.SearchCriteriaReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.dto.resp.brand.BrandsDTO;
import by.iba.dto.resp.car.CarDTO;
import by.iba.dto.resp.car.CarsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class BrandControllerImpl implements BrandController {

    private final BrandService brandService;

    @Override
    public ResponseEntity<BrandsDTO> getBrands() {
        List<BrandDTO> brands = brandService.getAll();

        return ResponseEntity
                .ok()
                .body(new BrandsDTO(brands));
    }

    @Override
    public ResponseEntity<BrandDTO> createBrand(@RequestBody @Valid BrandReqDTO brandReqDTO, BindingResult bindingResult) {
        BrandDTO brand = brandService.create(brandReqDTO);

        return ResponseEntity
                .ok()
                .body(brand);
    }

    @Override
    public ResponseEntity<RespStatusDTO> deleteBrand(@PathVariable Long id) {

        brandService.delete(id);

        return ResponseEntity
                .status(200)
                .body(new RespStatusDTO("BRAND_DELETED"));
    }

}
