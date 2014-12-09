import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.annotation.Nullable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 */
public class UnitTestRunner extends HttpServlet {

    public static final String PACKAGE_NAME = "test";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JUnitCore core = new JUnitCore();
        Result run = core.run(Request.classes(ReflectionHelper.getTestClasses(PACKAGE_NAME)));
        List<Failure> failures = run.getFailures();
        int runCount = run.getRunCount();


        String s = Joiner.on("\r\n").join(Iterables.transform(failures, new Function<Failure, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Failure failure) {
                return failure.getTestHeader() + ": " + Strings.nullToEmpty(failure.getMessage()) + failure.getTrace();

            }
        }));

        if (failures.size() > 0) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }

        PrintWriter writer = resp.getWriter();
        String total = "Total " + runCount + " Failures " + failures.size();
        writer.println(total);
        writer.write(s);
        writer.flush();
        writer.close();

    }


}
