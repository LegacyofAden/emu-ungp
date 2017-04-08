package org.l2junity.gameserver.retail;

import java.lang.annotation.*;

/**
 * @author ANZO
 * @since 09.04.2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface AiEventId {
    int value();
    String name() default "";
}
