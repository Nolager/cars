package cl.andres.cars.controller;

import cl.andres.cars.service.BrandServiceImpl;
import cl.andres.cars.model.Brand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {

    @Mock
    BrandServiceImpl service;

    @InjectMocks
    BrandController controller;

    @Mock
    Brand brand;

    private final Integer BRAND_ID = 1;

    @Test
    @DisplayName("Should return list of brands")
    public void brandsTest() {
        ArrayList<Brand> brands = new ArrayList<>();
        brands.add(brand);
        when(service.getBrands()).thenReturn(brands);
        ResponseEntity<List<Brand>> response = controller.brands();

        assertEquals(response.getBody().size(), brands.size());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).getBrands();
    }

    @Test
    @DisplayName("Should get one brand")
    public void oneBrandTest() {
        when(service.getBrand(BRAND_ID)).thenReturn(Optional.of(brand));
        ResponseEntity<Optional<Brand>> response = controller.oneBrand(BRAND_ID);

        assertEquals(response.getBody().get(), brand);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).getBrand(BRAND_ID);
    }

    @Test
    @DisplayName("Should call createBrand")
    public void saveBrand() {
        ResponseEntity<String> response = controller.saveBrand(brand);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).createBrand(brand);
    }

    @Test
    @DisplayName("Should call deleteBrand")
    public void deleteBrand() {
        ResponseEntity<String> response = controller.deleteBrand(BRAND_ID);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).deleteBrand(BRAND_ID);
    }

    @Test
    @DisplayName("Should call updateBrand")
    public void putBrand() {
        ResponseEntity<String> response = controller.putBrand(brand);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).updateBrand(brand);
    }
}