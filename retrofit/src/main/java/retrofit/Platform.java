/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */
package retrofit;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

class Platform {
  private static final Platform PLATFORM = findPlatform();

  static Platform get() {
    return PLATFORM;
  }

  private static Platform findPlatform() {
    try {
      Class.forName("android.os.Build");
      if (Build.VERSION.SDK_INT != 0) {
        return new Android();
      }
    } catch (ClassNotFoundException ignored) {
    }
    /**
    try {
      Class.forName("java.util.Optional");
      return new Java8();
    } catch (ClassNotFoundException ignored) {
    }*/
    return new Platform();
  }

  CallAdapter.Factory defaultCallAdapterFactory(Executor callbackExecutor) {
    if (callbackExecutor != null) {
      return new ExecutorCallAdapterFactory(callbackExecutor);
    }
    return DefaultCallAdapter.FACTORY;
  }

  boolean isDefaultMethod(Method method) {
    return false;
  }

  Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object, Object... args)
      throws Throwable {
    throw new UnsupportedOperationException();
  }

  /**
  @IgnoreJRERequirement // Only classloaded and used on Java 8.
  static class Java8 extends Platform {
    @Override boolean isDefaultMethod(Method method) {
      return method.isDefault();
    }

    @Override Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object,
        Object... args) throws Throwable {
      return MethodHandles.lookup()
          .in(declaringClass)
          .unreflectSpecial(method, declaringClass)
          .bindTo(object)
          .invokeWithArguments(args);
    }
  }*/

  static class Android extends Platform {
    @Override CallAdapter.Factory defaultCallAdapterFactory(Executor callbackExecutor) {
      if (callbackExecutor == null) {
        callbackExecutor = new MainThreadExecutor();
      }
      return new ExecutorCallAdapterFactory(callbackExecutor);
    }

    static class MainThreadExecutor implements Executor {
      private final Handler handler = new Handler(Looper.getMainLooper());

      @Override public void execute(Runnable r) {
        handler.post(r);
      }
    }
  }
}
