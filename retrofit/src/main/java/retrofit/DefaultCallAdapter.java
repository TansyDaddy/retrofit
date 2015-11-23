/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * A call adapter that uses the same thread for both I/O and application-level callbacks. For
 * synchronous calls this is the application thread making the request; for asynchronous calls this
 * is a thread provided by OkHttp's dispatcher.
 */
final class DefaultCallAdapter implements CallAdapter<Call<?>> {
  static final Factory FACTORY = new Factory() {
    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
      if (Utils.getRawType(returnType) != Call.class) {
        return null;
      }
      Type responseType = Utils.getCallResponseType(returnType);
      return new DefaultCallAdapter(responseType);
    }
  };

  private final Type responseType;

  DefaultCallAdapter(Type responseType) {
    this.responseType = responseType;
  }

  @Override public Type responseType() {
    return responseType;
  }

  @Override public <R> Call<R> adapt(Call<R> call) {
    return call;
  }
}
