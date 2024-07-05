import app.common.exceptions.NoDataFoundException;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import app.Main;
import app.dictionaries.registertype.ProductRegisterType;
import app.dictionaries.registertype.ProductRegisterTypeRepo;
import app.dictionaries.registertype.RegisterTypeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
public class ProductRegisterTypeTests {
    @Mock
    public ProductRegisterTypeRepo typeRepo;

    @InjectMocks
    RegisterTypeServiceImpl typeService;
    //@Test
    void testTypeNotFound(){
        Mockito.doReturn(null).when(typeRepo).findByCode(
                Mockito.anyString());

        assertThrows(NoDataFoundException.class,
                () -> typeService.findByCode(Mockito.anyString()));
    }
    //@Test
    void testTypeFound(){
        Mockito.doReturn(new ProductRegisterType()).when(typeRepo).findByCode(
                Mockito.anyString());
        Assertions.assertDoesNotThrow(() -> typeService.findByCode(Mockito.anyString()));

    }}
