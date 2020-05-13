package mock;

import com.dummy.api.springbootconfig.Model.Emp_Pojo;
import com.dummy.api.springbootconfig.SpringApplication;
import com.dummy.api.springbootconfig.controller.Emp_Repository;
import com.dummy.api.springbootconfig.controller.Emp_Services;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplication.class)
public class Mocking_Mockito_MockMvc {

    @Autowired
    SpringApplication springApplication;

    @Mock
    Emp_Pojo emp_pojo;

    @MockBean
            @Autowired
    RestTemplate restTemplate;

    @Mock
    Emp_Repository emp_repository;

    @InjectMocks
    Emp_Services emp_services;

    RestTemplateBuilder builder;
    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();
    List<Emp_Pojo> list;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        restTemplate = springApplication.getRestTemplate(builder);
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
    }
    @Test
    public void testSetup() {
        Assert.assertNotNull(emp_pojo);
    }
    public void listOfEmployee(){
        list=new ArrayList<Emp_Pojo>();
        Emp_Pojo emp=new Emp_Pojo("abc","26","12344",1);
        Emp_Pojo emp2=new Emp_Pojo("def","6","123",2);
        Emp_Pojo emp1=new Emp_Pojo("abcdff","26","12344",4);

        list.add(emp);
        list.add(emp1);
        list.add(emp2);
    }
    @Test
    public void getAllEmployee(){
        listOfEmployee();
        when(emp_repository.getAllEmp()).thenReturn(list);
        List<Emp_Pojo> getAll=emp_services.getAllEmp();
        Assert.assertEquals(3,getAll.size());
        verify(emp_repository,times(1)).getAllEmp();

        this.mockServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo("/getEmps"))
                .andRespond(withSuccess());

        this.mockServer.verify();
    }
    @Test
    public void getEmployee(){
        listOfEmployee();
        when(emp_repository.getEmp(4)).thenReturn(emp_pojo);
        Emp_Pojo getEmployee=emp_services.getEmp(4);
        MockMvcResultMatchers.jsonPath("$.emp_Name", Matchers.is("def"));
        verify(emp_repository,times(1)).getEmp(4);


   //           expect(requestTo("/getEmp/1"));  //http://localhost:8080/employee/E001
//                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON));
this.mockServer.verify();


    }
}