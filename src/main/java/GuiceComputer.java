import com.google.inject.Injector;
import org.junit.runner.Computer;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/**
 */
public class GuiceComputer extends Computer {


    private final Injector guiceInjector;

    public GuiceComputer(Injector guiceInjector) {
        super();
        this.guiceInjector = guiceInjector;
    }

    /**
     * Create a single-class runner for {@code testClass}, using {@code builder}
     *
     * @param builder
     * @param testClass
     */
    @Override
    protected Runner getRunner(RunnerBuilder builder, Class<?> testClass) throws Throwable {
        return super.getRunner(new RunnerBuilder() {
            @Override
            public Runner runnerForClass(Class<?> testClass) throws Throwable {
                return new GuiceBasedClassRunner(testClass, guiceInjector);
            }
        }, testClass);
    }
}
