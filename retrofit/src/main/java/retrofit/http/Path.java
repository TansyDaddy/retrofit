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
 * Named replacement in the URL path. Values are converted to string using
 * {@link String#valueOf(Object)} and URL encoded.
 * <p>
 * Simple example:
 * <pre>
 * &#64;GET("/image/{id}")
 * void example(@Path("id") int id);
 * </pre>
 * Calling with {@code foo.example(1)} yields {@code /image/1}.
 * <p>
 * Values are URL encoded by default. Disable with {@code encode=false}.
 * <pre>
 * &#64;GET("/user/{name}")
 * void encoded(@Path("name") String name);
 *
 * &#64;GET("/user/{name}")
 * void notEncoded(@Path(value="name", encode=false) String name);
 * </pre>
 * Calling {@code foo.encoded("John+Doe")} yields {@code /user/John%2BDoe} whereas
 * {@code foo.notEncoded("John+Doe")} yields {@code /user/John+Doe}.
 * <p>
 * Path parameters may not be {@code null}.
 */
@Documented
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface Path {
  String value();

  /**
   * Specifies whether the argument value to the annotated method parameter is already URL encoded.
   */
  boolean encoded() default false;
}
