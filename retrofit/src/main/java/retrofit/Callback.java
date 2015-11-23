/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

/**
 * Communicates responses from a server or offline requests. One and only one method will be
 * invoked in response to a given request.
 * <p>
 * Callback methods are executed using the {@link Retrofit} callback executor. When none is
 * specified, the following defaults are used:
 * <ul>
 * <li>Android: Callbacks are executed on the application's main (UI) thread.</li>
 * <li>JVM: Callbacks are executed on the background thread which performed the request.</li>
 * </ul>
 *
 * @param <T> expected response type
 */
public interface Callback<T> {
  /** Successful HTTP response. */
  void onResponse(Response<T> response, Retrofit retrofit);

  /** Invoked when a network or unexpected exception occurred during the HTTP request. */
  void onFailure(Throwable t);
}
