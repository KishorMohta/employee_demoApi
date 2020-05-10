package mock;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Mocking_MockIto {

    @Mock
    Emp_Pojo emp_pojo;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testSetup(){
        Assert.assertNotNull(emp_pojo);
    }

}
