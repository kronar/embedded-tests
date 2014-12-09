package itest;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.junit.Assert;
import org.junit.Test;

/**
 */
public class ITestA {

    @Inject
    @Named("val")
    private String valueField;


    @Test
    public void testA(){
        Assert.assertEquals(valueField, "b");
    }
}
