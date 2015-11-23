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
 * Denotes a single part of a multi-part request.
 * <p/>
 * The parameter type on which this annotation exists will be processed in one of two ways:
 * <ul>
 * <li>If the type is {@link com.squareup.okhttp.RequestBody RequestBody} the value will be used
 * directly with its content type.</li>
 * <li>Other object types will be converted to an appropriate representation by using
 * {@linkplain Converter a converter}.</li>
 * </ul>
 * <p/>
 * Values may be {@code null} which will omit them from the request body.
 * <p/>
 * <pre>
 * &#64;Multipart
 * &#64;POST("/")
 * Call&lt;<ResponseBody> example(
 *     &#64;Part("description") String description,
 *     &#64;Part(value = "image", encoding = "8-bit") RequestBody image);
 * </pre>
 * <p/>
 * Part parameters may not be {@code null}.
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Part {
    String value();

    /**
     * The {@code Content-Transfer-Encoding} of this part.
     */
    String encoding() default "binary";
}
