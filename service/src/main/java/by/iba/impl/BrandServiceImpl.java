package by.iba.impl;

import by.iba.BrandService;
import by.iba.dto.req.brand.BrandReqDTO;
import by.iba.dto.resp.brand.BrandDTO;
import by.iba.entity.car.CarBrand;
import by.iba.exception.ResourceNotFoundException;
import by.iba.mapper.BrandMapper;
import by.iba.repository.CarBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final CarBrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public CarBrand getByName(String name) {
        return brandRepository.findCarBrandByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("CAR_BRAND_NOT_FOUND"));
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandRepository.findAll()
                .stream()
                .map(brandMapper::fillInDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BrandDTO create(BrandReqDTO brandReqDTO) {

        CarBrand brand = brandMapper.fillFromReq(brandReqDTO);

        brand = brandRepository.save(brand);

        return brandMapper.fillInDTO(brand);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
