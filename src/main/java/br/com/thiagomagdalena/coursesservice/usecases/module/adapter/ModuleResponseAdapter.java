package br.com.thiagomagdalena.coursesservice.usecases.module.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleResponse;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleResponseAdapter extends AbstractAdapter<Module, ModuleResponse> {

    public ModuleResponseAdapter(JsonUtils jsonUtils) {
        super(ModuleResponse.class, jsonUtils);
    }
}
