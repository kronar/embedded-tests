import com.google.inject.Injector;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 */
public class GuiceBasedClassRunner extends BlockJUnit4ClassRunner {
    private final Injector injector;


    public GuiceBasedClassRunner(Class<?> klass, Injector injector) throws InitializationError {
        super(klass);
        this.injector = injector;
    }


    @Override
    protected Object createTest() throws Exception {
        return injector.getInstance(getTestClass().getJavaClass());
    }
}
