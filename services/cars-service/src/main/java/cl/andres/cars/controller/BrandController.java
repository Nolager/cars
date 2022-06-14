package cl.andres.cars.controller;

import cl.andres.cars.dto.response.GenericResponseDTO;
import cl.andres.cars.model.Brand;
import cl.andres.cars.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "Get list of all brands")
    @ApiResponse(responseCode = "200",
            description = "Found brands",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Brand.class))})
    @GetMapping
    public ResponseEntity<GenericResponseDTO> brands() {
        return ResponseEntity.ok(brandService.getBrands());
    }

    @Operation(summary = "Get a brand by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Found the brand",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Brand.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found",
                    content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> getBrandById(@Parameter(description = "id of brand to be searched")
                                                    @PathVariable long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @Operation(summary = "Saves a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Brand created",
                    content = @Content(schema = @Schema(implementation = Brand.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Brand already exists")
    })
    @PostMapping
    public ResponseEntity<GenericResponseDTO> saveBrand(
            @Parameter(description = "Brand to create. Cannot be null or empty",
                    required = true,
                    schema = @Schema(implementation = Brand.class))
            @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.createBrand(brand));
    }

    @Operation(summary = "Delete brand by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Brand deleted")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deleteBrand(@Parameter(description = "id of brand to be deleted")
                                              @PathVariable long id) {
        return ResponseEntity.ok(brandService.deleteBrand(id));
    }

    @Operation(summary = "Update an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand updated"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping
    public ResponseEntity<GenericResponseDTO> putBrand(
            @Parameter(description = "Brand to create. Cannot be null or empty",
                    required = true,
                    schema = @Schema(implementation = Brand.class))
            @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.updateBrand(brand));
    }
}
