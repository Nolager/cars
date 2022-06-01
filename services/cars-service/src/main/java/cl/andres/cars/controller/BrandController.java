package cl.andres.cars.controller;

import cl.andres.cars.model.Brand;
import cl.andres.cars.service.BrandServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandServiceImpl brandService;

    @Operation(summary = "Get list of all brands")
    @ApiResponse(responseCode = "200",
            description = "Found brands",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Brand.class))})
    @GetMapping
    public ResponseEntity<List<Brand>> brands() {
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
    public ResponseEntity<Optional<Brand>> oneBrand(@Parameter(description = "id of brand to be searched")
                                                    @PathVariable Integer id) {
        return ResponseEntity.ok(brandService.getBrand(id));
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
    public ResponseEntity<String> saveBrand(
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
    public ResponseEntity<String> deleteBrand(@Parameter(description = "id of brand to be deleted")
                                              @PathVariable Integer id) {
        return ResponseEntity.ok(brandService.deleteBrand(id));
    }

    @Operation(summary = "Update an existing brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand updated"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping
    public ResponseEntity<String> putBrand(
            @Parameter(description = "Brand to create. Cannot be null or empty",
                    required = true,
                    schema = @Schema(implementation = Brand.class))
            @RequestBody Brand brand) {
        return ResponseEntity.ok(brandService.updateBrand(brand));
    }
}
