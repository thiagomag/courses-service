package br.com.thiagomagdalena.coursesservice.usecases.lesson.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.lesson.LessonResponse;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Lesson;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class LessonResponseAdapter extends AbstractAdapter<Lesson, LessonResponse> {

    public LessonResponseAdapter(JsonUtils jsonUtils) {
        super(LessonResponse.class, jsonUtils);
    }
}
