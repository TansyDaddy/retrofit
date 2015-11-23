/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/** Make a PATCH request to a REST path relative to base URL. */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface PATCH {
  String value() default "";
}
