import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

/**
 */
public class MainModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(String.class).annotatedWith(Names.named("val")).toInstance("a");
    }
}
