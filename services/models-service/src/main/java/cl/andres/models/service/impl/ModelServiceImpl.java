package cl.andres.models.service.impl;

import cl.andres.models.model.Model;
import cl.andres.models.repository.ModelRepository;
import cl.andres.models.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> getModels() {
        return modelRepository.findAll();
    }
}
