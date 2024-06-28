import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import app.Main;
import app.controllers.register.CreateProductRegisterRequest;

import java.math.BigInteger;

//@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
//@WebMvcTest(ProductRegisterController.class)
public class ProductRegisterControllerTests {

    // Тестируем контроллер
    // Тест 1. Состав данных полный
    // Тест 2. Состав данных неполный, возвращается BAD_REQUEST

    //@Test
    void TestRequestParamsIsOK() {
        var req = new CreateProductRegisterRequest();
        //req.setInstanceId(Mockito.any(BigInteger.class)); //TODO так почему-то не работает
        req.setInstanceId(BigInteger.valueOf(1L));
        //Assertions.assertDoesNotThrow(() -> requestValidator.checkRequest(req));
    }
    //@Test
    void TestRequestBadParams() {
        var req = new CreateProductRegisterRequest();
       // var thrown = Assertions.assertThrows(BadRequestException.class, (() -> requestValidator.checkRequest(req)));
    }


}

