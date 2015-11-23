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
 * Query parameter keys and values appended to the URL.
 * <p>
 * Both keys and values are converted to strings using {@link String#valueOf(Object)}. Values are
 * URL encoded and {@code null} will not include the query parameter in the URL. {@code null} keys
 * are not allowed.
 * <p>
 * Simple Example:
 * <pre>
 * &#64;GET("/search")
 * void list(@QueryMap Map&lt;String, String&gt; filters);
 * </pre>
 * Calling with {@code foo.list(ImmutableMap.of("foo", "bar", "kit", "kat"))} yields
 * {@code /search?foo=bar&kit=kat}.
 * <p>
 * Map keys and values representing parameter values are URL encoded by default. Specify
 * {@link #encoded() encoded=true} to change this behavior.
 * <pre>
 * &#64;GET("/search")
 * void list(@QueryMap(encoded=true) Map&lt;String, String&gt; filters);
 * </pre>
 * Calling with {@code foo.list(ImmutableMap.of("foo", "foo+foo"))} yields
 * {@code /search?foo=foo%2Bbar}.
 *
 * @see Query
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface QueryMap {
  /** Specifies whether parameter names and values are already URL encoded. */
  boolean encoded() default false;
}
