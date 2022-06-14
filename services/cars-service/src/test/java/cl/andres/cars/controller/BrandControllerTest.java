package cl.andres.cars.controller;

import cl.andres.cars.dto.response.GenericResponseDTO;
import cl.andres.cars.service.impl.BrandServiceImpl;
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
import java.util.Arrays;
import java.util.Collections;

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
        GenericResponseDTO brandResponse = new GenericResponseDTO("Records found", brands);
        when(service.getBrands()).thenReturn(brandResponse);
        ResponseEntity<GenericResponseDTO> response = controller.brands();

        assertEquals(response.getBody().getRecords().size(), brands.size());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).getBrands();
    }

    @Test
    @DisplayName("Should get one brand")
    public void oneBrandTest() {
        GenericResponseDTO brandResponse = new GenericResponseDTO("Record found", Collections.singletonList(brand));

        when(service.getBrandById(BRAND_ID)).thenReturn(brandResponse);
        ResponseEntity<GenericResponseDTO> response = controller.getBrandById(BRAND_ID);

        assertEquals(response.getBody(), brandResponse);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).getBrandById(BRAND_ID);
    }

    @Test
    @DisplayName("Should call createBrand")
    public void saveBrand() {
        ResponseEntity<GenericResponseDTO> response = controller.saveBrand(brand);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).createBrand(brand);
    }

    @Test
    @DisplayName("Should call deleteBrand")
    public void deleteBrand() {
        ResponseEntity<GenericResponseDTO> response = controller.deleteBrand(BRAND_ID);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).deleteBrand(BRAND_ID);
    }

    @Test
    @DisplayName("Should call updateBrand")
    public void putBrand() {
        ResponseEntity<GenericResponseDTO> response = controller.putBrand(brand);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verify(service).updateBrand(brand);
    }
}