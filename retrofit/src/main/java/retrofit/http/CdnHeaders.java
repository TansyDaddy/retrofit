/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */

package retrofit.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Make a HEAD request to a REST path relative to base URL.
 * <p/>
 * Note: HEAD requests must use {@link Void} as the response type since there is no body content.
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface CdnHeaders {
    String value() default "";
}
