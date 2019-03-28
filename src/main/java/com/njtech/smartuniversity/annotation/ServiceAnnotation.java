package com.njtech.smartuniversity.annotation;

import java.lang.annotation.*;

/**
 * Created by ritchie on 6/29/18
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceAnnotation {
    String description() default "";

}
