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

import android.text.TextUtils;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;

import java.util.concurrent.TimeUnit;

public final class RequestFactory {
  private final String method;
  private final BaseUrl baseUrl;
  private final String relativeUrl;
  private final Headers headers;
  private final MediaType contentType;
  private final boolean hasBody;
  private final boolean isFormEncoded;
  private final boolean isMultipart;
  private final RequestBuilderAction[] requestBuilderActions;

    public enum CachePloy {
        MAX_AGE_0, MAX_STALE, FORCE_NETWORK, FORCE_CACHE
    }

  RequestFactory(String method, BaseUrl baseUrl, String relativeUrl, Headers headers,
      MediaType contentType, boolean hasBody, boolean isFormEncoded, boolean isMultipart,
      RequestBuilderAction[] requestBuilderActions) {
    this.method = method;
    this.baseUrl = baseUrl;
    this.relativeUrl = relativeUrl;
    this.headers = headers;
    this.contentType = contentType;
    this.hasBody = hasBody;
    this.isFormEncoded = isFormEncoded;
    this.isMultipart = isMultipart;
    this.requestBuilderActions = requestBuilderActions;
  }

  Request create(Object... args) {
    RequestBuilder requestBuilder =
        new RequestBuilder(method, baseUrl.url(), relativeUrl, headers, contentType, hasBody,
            isFormEncoded, isMultipart);

    if (args != null) {
      RequestBuilderAction[] actions = requestBuilderActions;
      if (actions.length != args.length) {
        throw new IllegalArgumentException("Argument count ("
            + args.length
            + ") doesn't match action count ("
            + actions.length
            + ")");
      }
      for (int i = 0, count = args.length; i < count; i++) {
        actions[i].perform(requestBuilder, args[i]);
      }
    }

    return requestBuilder.build();
  }

    /**
     * 增加动态传入接口缓存策略
     * @add by FanLei
     */
    Request create(CachePloy cachePloy, Object... args) {
        Request.Builder reqBuilder = null;

        if (cachePloy == CachePloy.FORCE_NETWORK) {// 强制网络
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK);
        } else if (cachePloy == CachePloy.FORCE_CACHE) {// 强制缓存（onIfCached+maxStale=MAX.VALUE）
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE);
        } else if (cachePloy == CachePloy.MAX_AGE_0) {// 优先网络
            reqBuilder = new Request.Builder().cacheControl(new CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build());
        } else if (cachePloy == CachePloy.MAX_STALE) {// 优先缓存(未过期缓存，默认30天)
            reqBuilder = new Request.Builder().cacheControl(new CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .build());
        }

        RequestBuilder requestBuilder =
                new RequestBuilder(method, baseUrl.url(), relativeUrl, headers, contentType, hasBody,
                        isFormEncoded, isMultipart, reqBuilder);

        if (args != null) {
            RequestBuilderAction[] actions = requestBuilderActions;
            if (actions.length != args.length) {
                throw new IllegalArgumentException("Argument count ("
                        + args.length
                        + ") doesn't match action count ("
                        + actions.length
                        + ")");
            }
            for (int i = 0, count = args.length; i < count; i++) {
                actions[i].perform(requestBuilder, args[i]);
            }
        }

        return requestBuilder.build();
    }

    /**
     * 增加动态传入baseURL和接口缓存策略
     * @add by FanLei
     */
    Request create(String dymicBaseUrl, CachePloy cachePloy, Object... args) {
        Request.Builder reqBuilder = null;

        if (cachePloy == CachePloy.FORCE_NETWORK) {// 强制网络
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK);
        } else if (cachePloy == CachePloy.FORCE_CACHE) {// 强制缓存（onIfCached+maxStale=MAX.VALUE）
            reqBuilder = new Request.Builder().cacheControl(CacheControl.FORCE_CACHE);
        } else if (cachePloy == CachePloy.MAX_AGE_0) {// 优先网络
            reqBuilder = new Request.Builder().cacheControl(new CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build());
        } else if (cachePloy == CachePloy.MAX_STALE) {// 优先缓存(未过期缓存，默认30天)
            reqBuilder = new Request.Builder().cacheControl(new CacheControl.Builder()
                    .maxStale(30, TimeUnit.DAYS)
                    .build());
        }

        HttpUrl baseHttpUrl = baseUrl.url();
        if (!TextUtils.isEmpty(dymicBaseUrl)) {
            baseHttpUrl = HttpUrl.parse(dymicBaseUrl);
        }

        RequestBuilder requestBuilder =
                new RequestBuilder(method, baseHttpUrl, relativeUrl, headers, contentType, hasBody,
                        isFormEncoded, isMultipart, reqBuilder);

        if (args != null) {
            RequestBuilderAction[] actions = requestBuilderActions;
            if (actions.length != args.length) {
                throw new IllegalArgumentException("Argument count ("
                        + args.length
                        + ") doesn't match action count ("
                        + actions.length
                        + ")");
            }
            for (int i = 0, count = args.length; i < count; i++) {
                actions[i].perform(requestBuilder, args[i]);
            }
        }

        return requestBuilder.build();
    }

}
