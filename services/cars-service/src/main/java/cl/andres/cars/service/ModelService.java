package cl.andres.cars.service;

import cl.andres.cars.model.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ModelService {

    private RestTemplate restTemplate;

    @Value("${services.model-url}")
    private String serviceUrl;

    ModelService() {
        restTemplate = new RestTemplate();
    }

    public List<Model> getModels() {
        Model[] models = restTemplate.getForObject(serviceUrl, Model[].class);
        return Arrays.asList(models);
    }
}
