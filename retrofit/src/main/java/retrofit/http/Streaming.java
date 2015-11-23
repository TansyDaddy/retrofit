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
 * Treat the response body on methods returning {@link com.squareup.okhttp.Response Response} as is,
 * i.e. without converting {@link com.squareup.okhttp.Response#body() body()} to {@code byte[]}.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Streaming {
}
