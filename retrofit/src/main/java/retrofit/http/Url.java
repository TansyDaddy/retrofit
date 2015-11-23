/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Relative URL resolved against the {@linkplain Retrofit#baseUrl() base URL}.
 * <pre>
 * &#64;GET
 * void list(@Url String url);
 * </pre>
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Url {
}
