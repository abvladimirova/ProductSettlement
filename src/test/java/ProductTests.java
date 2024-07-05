import app.common.exceptions.NoDataFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import app.Main;
import app.product.TppProduct;
import app.product.TppProductRepo;
import app.product.TppProductServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class ProductTests {

    @Mock
    public static TppProductRepo mockProductRepo;

    @InjectMocks
    public static TppProductServiceImpl ProductService;

    @BeforeAll
    public static void initData () {

    }

    //@Test
    void testGetByIdProductNotFound() {
        Mockito.doReturn(Optional.empty()).when(mockProductRepo).findById(
                Mockito.any(BigInteger.class)
                );
        Assertions.assertThrows(NoDataFoundException.class, (()->ProductService.getProduct(BigInteger.valueOf(1))));
    }

    //@Test
    void testGetByIdProductFound() throws NoDataFoundException {
        var productExpected = new TppProduct();
        Mockito.doReturn(Optional.of(productExpected)).when(mockProductRepo).findById(
                Mockito.any(BigInteger.class)
        );
        Assertions.assertEquals(productExpected, ProductService.getProduct(BigInteger.valueOf(1)));
    }

    //@Test
    void testNewProduct() {
        var productExpected = new TppProduct();
        var random = new Random();
        productExpected.setId(BigInteger.valueOf(1))
                        .setClientId(BigInteger.valueOf(random.nextLong()))
                        .setNumber(String.valueOf(random.nextInt()))
                        .setDateOfConclusion(Date.valueOf("2024-01-01"))
                        .setProductCodeId(BigInteger.valueOf(random.nextInt()))
                        .setClientId(BigInteger.valueOf(random.nextInt()))
                        .setProductType(String.valueOf(random.nextInt()))
                        .setPriority(BigInteger.valueOf(random.nextInt()))
                        .setPenaltyRate(random.nextFloat())
                        .setNso(BigDecimal.valueOf(random.nextInt()))
                        .setThresholdAmount(BigDecimal.valueOf(random.nextInt()))
                        .setInterestRateType(String.valueOf(random.nextInt()));

        Mockito.doReturn(productExpected).when(mockProductRepo).save(productExpected);
        Mockito.verify(mockProductRepo.save(Mockito.any(TppProduct.class)) , Mockito.atMostOnce());
    }

}