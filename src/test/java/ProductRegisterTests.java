import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import app.Main;
import app.account.exceptions.AccountNotFoundException;
import app.account.exceptions.AccountPoolNotFound;
import app.account.service.AccountService;
import app.product.exceptions.ProductNotFoundException;
import app.controllers.register.CreateProductRegisterRequest;
import app.controllers.register.ProductRegisterController;
import app.productregister.entity.TppProductRegister;
import app.productregister.exceptions.DuplicateRecordException;
import app.productregister.service.TppProductRegisterService;
import app.registertype.entity.ProductRegisterType;
import app.registertype.exceptions.RegisterTypeNotFoundException;
import app.registertype.service.RegisterTypeService;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
public class ProductRegisterTests {
    @Mock
    AccountService accountService;
    @Mock TppProductRegisterService productRegisterService;
    @Mock  RegisterTypeService typeService;
    //@Mock RequestValidator<CreateProductRegisterRequest> requestValidator;

    @InjectMocks
    ProductRegisterController controller;
    //@Test
    void HasDuplicateProd() {
        var req = new CreateProductRegisterRequest();
        var t = new ProductRegisterType();
        //req.setInstanceId(Mockito.any(BigInteger.class)); //TODO так почему-то не работает
        req.setInstanceId(BigInteger.valueOf(1L));
        try {
            Mockito.doReturn(null).when(accountService).getAccountByParams(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
            Mockito.doReturn(t).when(typeService).findByCode(Mockito.anyString());
            Mockito.doReturn(new TppProductRegister()).when(productRegisterService).getProductRegister(Mockito.any(BigInteger.class), Mockito.any(ProductRegisterType.class));

            assertThrows(DuplicateRecordException.class,
                    () -> controller.createProductRegister(req));
        }
        catch (AccountNotFoundException | AccountPoolNotFound | RegisterTypeNotFoundException | ProductNotFoundException e) {
            Assertions.assertTrue(false);
        }
    }

}
