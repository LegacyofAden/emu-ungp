package org.l2junity.gameserver.retail;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface EventId {
    int value();
    String name() default "";
}
