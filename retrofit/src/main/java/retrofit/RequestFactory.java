/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

import android.text.TextUtils;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;

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

    /**
     * 增加动态传入baseURL和接口缓存策略
     *
     * @add by FanLei
     */
    Request create(String dynamicBaseUrl, CacheControl controlPloy, Object... args) {
        HttpUrl baseHttpUrl = baseUrl.url();
        if (!TextUtils.isEmpty(dynamicBaseUrl)) {
            baseHttpUrl = HttpUrl.parse(dynamicBaseUrl);
        }

        Request.Builder reqBuilder = reqBuilder = new Request.Builder().cacheControl(controlPloy);
        ;

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
