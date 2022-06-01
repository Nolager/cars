package cl.andres.cars.service;

import cl.andres.cars.model.Brand;
import cl.andres.cars.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrand(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public String createBrand(Brand brand) {
        brandRepository.save(brand);
        return "Brand saved!";
    }

    @Override
    public String deleteBrand(Integer id) {
        brandRepository.deleteById(id);
        return "Brand deleted!";
    }

    @Override
    public String updateBrand(Brand brand) {
        brandRepository.save(brand);
        return "Brand updated!";
    }
}
