package itest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Assert;
import org.junit.Test;

/**
 */
public class ITestB {

    @Inject
    @Named("val")
    private String value;


    @Test
    public void testB() {
        Assert.assertEquals(value.length(), 1);
    }
}
