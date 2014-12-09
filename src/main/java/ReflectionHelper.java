import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Set;

/**
 */
public final class ReflectionHelper {


    ReflectionHelper() {
    }

    public static Class<?>[] getTestClasses(String packageName) {
        MethodAnnotationsScanner scanner = new MethodAnnotationsScanner();
        Reflections reflector = new Reflections(packageName, scanner);

        Set<Method> test = reflector.getMethodsAnnotatedWith(org.junit.Test.class);
        FluentIterable<Class<?>> transform = FluentIterable.from(test).transform(new Function<Method, Class<?>>() {
            @Nullable
            @Override
            public Class<?> apply(@Nullable Method method) {
                return method.getDeclaringClass();
            }
        });


        ImmutableList<Class<?>> classes = transform.toList();

        Class<?>[] res = new Class[classes.size()];
        for (int i = 0; i < classes.size(); i++) {
            res[i] = classes.get(i);
        }
        return res;
    }


}
