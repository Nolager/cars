package cl.andres.cars.service.impl;

import cl.andres.cars.dto.response.GenericResponseDTO;
import cl.andres.cars.exception.ResourceNotFoundException;
import cl.andres.cars.model.Brand;
import cl.andres.cars.repository.BrandRepository;
import cl.andres.cars.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public GenericResponseDTO getBrands() {
        return new GenericResponseDTO("Records found", brandRepository.findAll());
    }

    @Override
    public GenericResponseDTO getBrandById(long id) {
        return brandRepository.findById(id)
                .map(brand -> new GenericResponseDTO(
                        "Record found",
                        Collections.singletonList(brand)))
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found Brand id " + id));
    }

    @Override
    public GenericResponseDTO createBrand(Brand brand) {
        brandRepository.save(brand);
        return GenericResponseDTO.builder().message("Brand saved!").build();
    }

    @Override
    public GenericResponseDTO deleteBrand(long id) {
        brandRepository.deleteById(id);
        return GenericResponseDTO.builder().message("Brand deleted!").build();
    }

    @Override
    public GenericResponseDTO updateBrand(Brand brand) {
        brandRepository.save(brand);
        return GenericResponseDTO.builder().message("Brand updated!").build();
    }
}
