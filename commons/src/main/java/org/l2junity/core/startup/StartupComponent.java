package org.l2junity.core.startup;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author n3k0nation, ANZO
 * @since 27.12.2016
 */
@IndexAnnotated
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartupComponent {
	String value();

	Class<?>[] dependency() default {};
}