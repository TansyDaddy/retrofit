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
 * Denotes name and value parts of a multi-part request.
 * <p/>
 * Values of the map on which this annotation exists will be processed in one of two ways:
 * <ul>
 * <li>If the type is {@link com.squareup.okhttp.RequestBody RequestBody} the value will be used
 * directly with its content type.</li>
 * <li>Other object types will be converted to an appropriate representation by using
 * {@linkplain Converter a converter}.</li>
 * </ul>
 * <p/>
 * <pre>
 * &#64;Multipart
 * &#64;POST("/upload")
 * Call&lt;ResponseBody> upload(
 *     &#64;Part("file") RequestBody file,
 *     &#64;PartMap Map&lt;String, RequestBody&gt; params);
 * </pre>
 *
 * @see Multipart
 * @see Part
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface PartMap {
    /**
     * The {@code Content-Transfer-Encoding} of the parts.
     */
    String encoding() default "binary";
}
