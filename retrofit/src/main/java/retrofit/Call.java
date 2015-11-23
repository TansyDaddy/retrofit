/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

import com.squareup.okhttp.CacheControl;

import java.io.IOException;

/**
 * An invocation of a Retrofit method that sends a request to a webserver and returns a response.
 * Each call yields its own HTTP request and response pair. Use {@link #clone} to make multiple
 * calls with the same parameters to the same webserver; this may be used to implement polling or
 * to retry a failed call.
 * <p/>
 * <p>Calls may be executed synchronously with {@link #execute}, or asynchronously with {@link
 * #enqueue}. In either case the call can be canceled at any time with {@link #cancel}. A call that
 * is busy writing its request or reading its response may receive a {@link IOException}; this is
 * working as designed.
 *
 * @modify by FanLei
 * add dynamicBaseUrl and cachePloy param
 */
public interface Call<T> extends Cloneable {
    Response<T> execute() throws IOException;

    Response<T> execute(String dynamicBaseUrl) throws IOException;

    Response<T> execute(RequestFactory.CachePloy cachePloy) throws IOException;

    Response<T> execute(CacheControl controlPloy) throws IOException;

    Response<T> execute(String dynamicBaseUrl, RequestFactory.CachePloy cachePloy) throws IOException;

    Response<T> execute(String dynamicBaseUrl, CacheControl controlPloy) throws IOException;

    void enqueue(Callback<T> callback);

    void enqueue(Callback<T> callback, String dynamicBaseUrl);

    void enqueue(Callback<T> callback, RequestFactory.CachePloy cachePloy);

    void enqueue(Callback<T> callback, CacheControl controlPloy);

    void enqueue(Callback<T> callback, String dynamicBaseUrl, RequestFactory.CachePloy cachePloy);

    void enqueue(Callback<T> callback, String dynamicBaseUrl, CacheControl controlPloy);

    void cancel();

    Call<T> clone();
}
