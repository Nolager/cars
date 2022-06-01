package cl.andres.cars.service;

import cl.andres.cars.model.Brand;
import cl.andres.cars.repository.BrandRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BrandServiceImplTest {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandRepository brandRepository;

    private final Integer BRAND_ID = 1;

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
        brandService.getBrand(BRAND_ID);
        verify(brandRepository).findById(BRAND_ID);
    }

    @Test
    @DisplayName("It should create a brand using save")
    public void saveBrandTest() {
        Brand brand = new Brand();
        brand.setName("Mercedes");
        String expectedReturnMessage = "Brand saved!";

        assertEquals(expectedReturnMessage, brandService.createBrand(brand));
        verify(brandRepository).save(brand);
    }

    @Test
    @DisplayName("It should delete sent brand using deleteById")
    public void deleteBrandTest() {
        String expectedReturnMessage = "Brand deleted!";

        assertEquals(expectedReturnMessage, brandService.deleteBrand(BRAND_ID));
        verify(brandRepository).deleteById(BRAND_ID);
    }

    @Test
    @DisplayName("It should update brand using save")
    public void updateBrandTest() {
        Brand brand = new Brand();
        brand.setId(BRAND_ID);
        brand.setName("Brand to update");
        String expectedReturnMessage = "Brand updated!";

        assertEquals(expectedReturnMessage, brandService.updateBrand(brand));
        verify(brandRepository).save(brand);
    }
}