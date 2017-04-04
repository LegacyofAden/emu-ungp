package org.l2junity.commons.config.annotation;

import org.atteo.classindex.IndexAnnotated;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ANZO
 */
@IndexAnnotated
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigFile {
	String name();

	String[] loadForPackages() default {};
}
