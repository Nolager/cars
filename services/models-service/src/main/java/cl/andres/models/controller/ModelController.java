package cl.andres.models.controller;

import cl.andres.models.model.Model;
import cl.andres.models.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @Operation(summary = "Get list of al models")
    @ApiResponse(responseCode = "200",
        description = "Found models",
        content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Model.class)) })
    @GetMapping
    public ResponseEntity<List<Model>> getModels() throws InterruptedException {

//        Thread.sleep(3000);

        return ResponseEntity.ok(modelService.getModels());
    }
}
