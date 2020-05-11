package mock;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import com.dummy.api.springbootconfig.SpringApplication;
import com.dummy.api.springbootconfig.controller.Emp_Services;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class)
public class Mocking_MockIto {

    @Autowired
    SpringApplication springApplication;
    @Mock
    Emp_Pojo emp_pojo;
    @Mock
    RestTemplate restTemplate;
    @Autowired
    @InjectMocks
    Emp_Services emp_services;
    RestTemplateBuilder builder;

    MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);
        restTemplate = springApplication.getRestTemplate(builder);
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
        mockMvc= MockMvcBuilders.standaloneSetup(emp_services).build();
    }

    @Test
    public void testSetup() {
        Assert.assertNotNull(emp_pojo);
    }

    @Test
    public void getMethod() throws Exception{

        mockMvc.perform(
                 get("/getEmp/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.emp_Name", Matchers.is("def")));
    }
    @Test
    public void postMethod() throws Exception{

        Emp_Pojo emp=new Emp_Pojo();
        emp.setEmp_id(1);
        emp.setEmp_Age("26");
        emp.setEmp_Name("abc");
        emp.setEmp_Sal("12456789");

    mockMvc.perform(post("/postEmp")
            .content(asJsonString(emp))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.emp_Name").exists());

    }

    @Test

    public void getAllEmployee() throws Exception{

        mockMvc.perform(
                get("/getEmps")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].emp_Name").isNotEmpty());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}