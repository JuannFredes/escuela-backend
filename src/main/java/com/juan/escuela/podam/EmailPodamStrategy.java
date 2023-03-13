package com.juan.escuela.podam;

import com.mifmif.common.regex.Generex;
import uk.co.jemos.podam.common.AttributeStrategy;

import javax.validation.constraints.Email;
import java.lang.annotation.Annotation;
import java.util.List;

public class EmailPodamStrategy implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> aClass, List<Annotation> list) {
        return "emailejemplo@gmail.com";
    }
}
