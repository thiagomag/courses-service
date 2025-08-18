package br.com.thiagomagdalena.coursesservice.usecases.course.adapter;

import br.com.thiagomagdalena.coursesservice.adapter.AbstractAdapter;
import br.com.thiagomagdalena.coursesservice.api.dto.course.CourseRequest;
import br.com.thiagomagdalena.coursesservice.persistance.domain.Course;
import br.com.thiagomagdalena.coursesservice.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class CourseAdapter extends AbstractAdapter<CourseRequest, Course> {

    public CourseAdapter(JsonUtils jsonUtils) {
        super(Course.class, jsonUtils);
    }
}
