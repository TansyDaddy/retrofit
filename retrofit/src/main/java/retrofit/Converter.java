/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Convert objects to and from their representation as HTTP bodies. Register a converter with
 * Retrofit using {@link Retrofit.Builder#addConverterFactory(Factory)}.
 */
public interface Converter<F, T> {
  T convert(F value) throws IOException;

  abstract class Factory {
    /**
     * Create a {@link Converter} for converting an HTTP response body to {@code type} or null if it
     * cannot be handled by this factory.
     */
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
      return null;
    }

    /**
     * Create a {@link Converter} for converting {@code type} to an HTTP request body or null if it
     * cannot be handled by this factory.
     */
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
      return null;
    }
  }
}
