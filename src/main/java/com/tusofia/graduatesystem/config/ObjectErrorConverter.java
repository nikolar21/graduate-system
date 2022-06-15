package com.tusofia.graduatesystem.config;

import javax.annotation.Nullable;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class ObjectErrorConverter implements Converter<ObjectError, String> {

  @Override
  @Nullable
  public String convert(@NonNull ObjectError objectError) {
    if (objectError instanceof FieldError fieldError) {
      return fieldError.getField() + " " + objectError.getDefaultMessage();

    } else {
      return objectError.getDefaultMessage();
    }
  }
}
