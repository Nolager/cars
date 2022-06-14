package cl.andres.cars.service;

import cl.andres.cars.dto.response.GenericResponseDTO;
import cl.andres.cars.model.Brand;
import cl.andres.cars.repository.BrandRepository;
import cl.andres.cars.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    Brand brand;

    private final long BRAND_ID = 1;

//    @BeforeEach
//    public void setUp() {}

    @Test
    @DisplayName("It should call the repository findAll")
    public void getAllBrandsTest() {
        brandService.getBrands();
        verify(brandRepository).findAll();
    }

    @Test
    @DisplayName("It should call the repository findById")
    public void getOneBrandTest() {
        when(brandRepository.findById(BRAND_ID)).thenReturn(Optional.of(brand));

        brandService.getBrandById(BRAND_ID);
        verify(brandRepository).findById(BRAND_ID);
    }

    @Test
    @DisplayName("It should create a brand using save")
    public void saveBrandTest() {
        Brand brand = new Brand();
        brand.setName("Mercedes");
        GenericResponseDTO expectedResponse = GenericResponseDTO.builder().message("Brand saved!").build();

        assertEquals(expectedResponse.getMessage(), brandService.createBrand(brand).getMessage());
        verify(brandRepository).save(brand);
    }

    @Test
    @DisplayName("It should delete sent brand using deleteById")
    public void deleteBrandTest() {
        GenericResponseDTO expectedResponse = GenericResponseDTO.builder().message("Brand deleted!").build();

        assertEquals(expectedResponse.getMessage(), brandService.deleteBrand(BRAND_ID).getMessage());
        verify(brandRepository).deleteById(BRAND_ID);
    }

    @Test
    @DisplayName("It should update brand using save")
    public void updateBrandTest() {
        Brand brand = new Brand();
        brand.setId(BRAND_ID);
        brand.setName("Brand to update");
        GenericResponseDTO expectedResponse = GenericResponseDTO.builder().message("Brand updated!").build();

        assertEquals(expectedResponse.getMessage(), brandService.updateBrand(brand).getMessage());
        verify(brandRepository).save(brand);
    }
}