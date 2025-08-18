package br.com.thiagomagdalena.coursesservice.configuration.deserializer.enums;

import br.com.thiagomagdalena.coursesservice.enums.CourseCategoryEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseCategoryEnumDeserializer extends AbstractEnumDeserializer<CourseCategoryEnum> {

    private static final String LOG_PREFIX = "[COURSE_CATEGORY_ENUM_DESERIALIZER]";

    @Override
    protected CourseCategoryEnum convertToEnum(String value) {
        return CourseCategoryEnum.findBy(value);
    }

    @Override
    protected String getLogPrefix() {
        return LOG_PREFIX;
    }

}
