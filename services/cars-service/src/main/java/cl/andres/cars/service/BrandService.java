package cl.andres.cars.service;

import cl.andres.cars.model.Brand;
import java.util.Optional;

interface BrandService {
    Iterable<Brand> getBrands();
    Optional<Brand> getBrand(Integer id);
    String createBrand(Brand brand);
    String deleteBrand(Integer id);
    String updateBrand(Brand brand);
}
