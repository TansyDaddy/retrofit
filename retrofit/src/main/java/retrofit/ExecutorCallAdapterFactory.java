/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit;

import com.squareup.okhttp.CacheControl;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

final class ExecutorCallAdapterFactory implements CallAdapter.Factory {
  private final Executor callbackExecutor;

  ExecutorCallAdapterFactory(Executor callbackExecutor) {
    this.callbackExecutor = callbackExecutor;
  }

  @Override
  public CallAdapter<Call<?>> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    if (Utils.getRawType(returnType) != Call.class) {
      return null;
    }
    final Type responseType = Utils.getCallResponseType(returnType);
    return new CallAdapter<Call<?>>() {
      @Override public Type responseType() {
        return responseType;
      }

      @Override public <R> Call<R> adapt(Call<R> call) {
        return new ExecutorCallbackCall<>(callbackExecutor, call);
      }
    };
  }

  static final class ExecutorCallbackCall<T> implements Call<T> {
    private final Executor callbackExecutor;
    private final Call<T> delegate;

    ExecutorCallbackCall(Executor callbackExecutor, Call<T> delegate) {
      this.callbackExecutor = callbackExecutor;
      this.delegate = delegate;
    }

    @Override
    public void enqueue(Callback<T> callback) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback));
    }

    @Override
    public void enqueue(Callback<T> callback, String dynamicBaseUrl) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback), dynamicBaseUrl);
    }

    @Override
    public void enqueue(Callback<T> callback, RequestFactory.CachePloy cachePloy) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback), cachePloy);
    }

    @Override
    public void enqueue(Callback<T> callback, CacheControl cacheControl) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback), cacheControl);
    }

    @Override
    public void enqueue(Callback<T> callback, String dynamicBaseUrl, RequestFactory.CachePloy cachePloy) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback), dynamicBaseUrl, cachePloy);
    }

    @Override
    public void enqueue(Callback<T> callback, String dynamicBaseUrl, CacheControl cacheControl) {
      delegate.enqueue(new ExecutorCallback<>(callbackExecutor, callback), dynamicBaseUrl, cacheControl);
    }

    @Override
    public Response<T> execute() throws IOException {
      return delegate.execute();
    }

    @Override
    public Response<T> execute(String dynamicBaseUrl) throws IOException {
      return delegate.execute(dynamicBaseUrl);
    }

    @Override
    public Response<T> execute(RequestFactory.CachePloy cachePloy) throws IOException {
      return delegate.execute(cachePloy);
    }

    @Override
    public Response<T> execute(CacheControl cacheControl) throws IOException {
      return delegate.execute(cacheControl);
    }

    @Override
    public Response<T> execute(String dynamicBaseUrl, RequestFactory.CachePloy cachePloy) throws IOException {
      return delegate.execute(dynamicBaseUrl, cachePloy);
    }

    @Override
    public Response<T> execute(String dynamicBaseUrl, CacheControl cacheControl) throws IOException {
      return delegate.execute(dynamicBaseUrl, cacheControl);
    }

    @Override public void cancel() {
      delegate.cancel();
    }

    @SuppressWarnings("CloneDoesntCallSuperClone") // Performing deep clone.
    @Override public Call<T> clone() {
      return new ExecutorCallbackCall<>(callbackExecutor, delegate.clone());
    }
  }

  static final class ExecutorCallback<T> implements Callback<T> {
    private final Executor callbackExecutor;
    private final Callback<T> delegate;

    ExecutorCallback(Executor callbackExecutor, Callback<T> delegate) {
      this.callbackExecutor = callbackExecutor;
      this.delegate = delegate;
    }

    @Override public void onResponse(final Response<T> response, final Retrofit retrofit) {
      callbackExecutor.execute(new Runnable() {
        @Override public void run() {
          delegate.onResponse(response, retrofit);
        }
      });
    }

    @Override public void onFailure(final Throwable t) {
      callbackExecutor.execute(new Runnable() {
        @Override public void run() {
          delegate.onFailure(t);
        }
      });
    }
  }
}
