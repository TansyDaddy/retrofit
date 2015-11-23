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
 * Query parameter appended to the URL.
 * <p>
 * Values are converted to strings using {@link String#valueOf(Object)} and then URL encoded.
 * {@code null} values are ignored. Passing a {@link java.util.List List} or array will result in a
 * query parameter for each non-{@code null} item.
 * <p>
 * Simple Example:
 * <pre>
 * &#64;GET("/list")
 * void list(@Query("page") int page);
 * </pre>
 * Calling with {@code foo.list(1)} yields {@code /list?page=1}.
 * <p>
 * Example with {@code null}:
 * <pre>
 * &#64;GET("/list")
 * void list(@Query("category") String category);
 * </pre>
 * Calling with {@code foo.list(null)} yields {@code /list}.
 * <p>
 * Array Example:
 * <pre>
 * &#64;GET("/list")
 * void list(@Query("category") String... categories);
 * </pre>
 * Calling with {@code foo.list("bar", "baz")} yields
 * {@code /list?category=bar&category=baz}.
 * <p>
 * Parameter names and values are URL encoded by default. Specify {@link #encoded() encoded=true}
 * to change this behavior.
 * <pre>
 * &#64;GET("/search")
 * void list(@Query(value="foo", encoded=true) String foo);
 * </pre>
 * Calling with {@code foo.list("foo+bar"))} yields {@code /search?foo=foo+bar}.
 *
 * @see QueryMap
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Query {
  /** The query parameter name. */
  String value();

  /**
   * Specifies whether the parameter {@linkplain #value() name} and value are already URL encoded.
   */
  boolean encoded() default false;
}
