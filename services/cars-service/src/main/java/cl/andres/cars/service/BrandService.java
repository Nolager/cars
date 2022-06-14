package cl.andres.cars.service;

import cl.andres.cars.dto.response.GenericResponseDTO;
import cl.andres.cars.model.Brand;

public interface BrandService {
    GenericResponseDTO getBrands();
    GenericResponseDTO getBrandById(long id);
    GenericResponseDTO createBrand(Brand brand);
    GenericResponseDTO deleteBrand(long id);
    GenericResponseDTO updateBrand(Brand brand);
}
