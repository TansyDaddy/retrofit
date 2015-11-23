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
 * Use a custom HTTP verb for a request.
 * <pre>{@code
 * interface Service {
 *   &#064;HTTP(method = "CUSTOM", path = "custom/endpoint/")
 *   Call<ResponseBody> customEndpoint();
 * }
 * }</pre>
 * This annotation can also used for sending {@code DELETE} with a request body:
 * <pre>{@code
 * interface Service {
 *   &#064;HTTP(method = "DELETE", path = "remove/", hasBody = true)
 *   Call<ResponseBody> deleteObject(@Body RequestBody object);
 * }
 * }</pre>
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface HTTP {
  String method();
  String path() default "";
  boolean hasBody() default false;
}
