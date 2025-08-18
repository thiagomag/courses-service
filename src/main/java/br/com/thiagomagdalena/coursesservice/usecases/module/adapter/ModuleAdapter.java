package br.com.thiagomagdalena.coursesservice.usecases.module.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.module.ModuleRequest;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Module;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class ModuleAdapter extends AbstractAdapter<ModuleRequest, Module> {

    public ModuleAdapter(JsonUtils jsonUtils) {
        super(Module.class, jsonUtils);
    }
}
