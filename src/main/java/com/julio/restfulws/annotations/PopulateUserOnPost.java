package com.julio.restfulws.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PopulateUserOnPost {

    String positionOfUserId() default "0";
    String positionOfPostObject() default "1";

}
