package br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonRequest;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class LessonAdapter extends AbstractAdapter<LessonRequest, Lesson> {

    public LessonAdapter(JsonUtils jsonUtils) {
        super(Lesson.class, jsonUtils);
    }
}
