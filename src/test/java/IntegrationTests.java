import app.Main;
import app.controllers.register.CreateProductRegisterRequest;
import app.productregister.entity.TppProductRegister;
import app.productregister.repository.TppProductRegisterRepo;
import app.productregister.service.TppProductRegisterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@SpringBootTest(classes = Main.class)
//@Testcontainers
public class IntegrationTests {


    private TppProductRegisterRepo tppProductRegisterRepo;
//    @Autowired
    private TppProductRegisterService tppProductRegisterService;
//    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.3"));

//    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.username", postgresContainer::getUsername);

        //registry.add("spring.liquibase.url", postgresContainer::getJdbcUrl);
        //registry.add("spring.liquibase.user", postgresContainer::getUsername);
        //registry.add("spring.liquibase.password", postgresContainer::getPassword);

        //registry.add("integrations.bgt.url", mockServerContainer::getEndpoint);

        //registry.add("spring.liquibase.enabled=", () -> true);
        //registry.add("spring.liquibase.change-log=", () -> "classpath:/db/changelog/db.changelog-master.yaml");
    }

//    @BeforeAll
    static void initData(@Autowired TppProductRegisterRepo tppProductRegisterRepo) {
        tppProductRegisterRepo.deleteAll();
    }

//    @Test
    public void test1(){
        assertEquals(0,tppProductRegisterRepo.count());
    }

/*@Test
public void test() {
    CreateProductRegisterRequest request = new CreateProductRegisterRequest();
    String requestStr = bgtObjectMapper.writeValueAsString(request);
    MvcResult result = mvc.perform(post(BGT_CALLBACK_URL).contentType(APPLICATION_JSON).content(requestStr)).andReturn();
    MockHttpServletResponse response = result.getResponse();

    assertEquals(BAD_REQUEST.value(),result.getResponse().getStatus());
    assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains("Ошибка запроса: Ошибка при валидации тела запроса. Некорректные значения полей"));
}*/
}