package br.com.thiagomagdalena.coursesservice.usecases.course.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseResponse;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Course;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class CourseResponseAdapter extends AbstractAdapter<Course, CourseResponse> {

    public CourseResponseAdapter(JsonUtils jsonUtils) {
        super(CourseResponse.class, jsonUtils);
    }
}
