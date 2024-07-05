import app.common.exceptions.NoDataFoundException;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import app.Main;
import app.account.AccountService;
import app.productregister.api.CreateProductRegisterRequest;
import app.controllers.ProductRegisterController;
import app.productregister.TppProductRegister;
import app.common.exceptions.DuplicateRecordException;
import app.productregister.TppProductRegisterService;
import app.dictionaries.registertype.ProductRegisterType;
import app.dictionaries.registertype.RegisterTypeService;

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
        catch (NoDataFoundException e) {
            Assertions.assertTrue(false);
        }
    }

}
