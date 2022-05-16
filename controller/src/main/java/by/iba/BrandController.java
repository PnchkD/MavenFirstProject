package by.iba;

import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.resp.RespStatusDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.dto.resp.brand.BrandsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/brands")
@CrossOrigin(origins = "*")
public interface BrandController {

    @GetMapping("/")
    ResponseEntity<BrandsDTO> getBrands();

    @PostMapping("/create")
    ResponseEntity<BrandDTO> createBrand(@RequestBody @Valid BrandReqDTO brandReqDTO, BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<RespStatusDTO> deleteBrand(@PathVariable Long id);

}
