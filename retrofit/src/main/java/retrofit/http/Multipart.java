/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Denotes that the request body is multi-part. Parts should be declared as parameters and
 * annotated with {@link Part @Part}.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Multipart {
}
