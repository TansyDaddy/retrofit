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
 * Replaces the header with the the value of its target.
 * <p>
 * <pre>
 * &#64;GET("/")
 * void foo(@Header("Accept-Language") String lang, Callback&lt;Response&gt; cb);
 * </pre>
 * <p>
 * Header parameters may be {@code null} which will omit them from the request. Passing a
 * {@link java.util.List List} or array will result in a header for each non-{@code null} item.
 * <p>
 * <strong>Note:</strong> Headers do not overwrite each other. All headers with the same name will
 * be included in the request.
 *
 * @author Adrian Cole (adrianc@netflix.com)
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Header {
  String value();
}
