import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import app.Main;
import app.product.entity.TppProduct;
import app.product.exceptions.ProductNotFoundException;
import app.product.repository.TppProductRepo;
import app.product.service.TppProductServiceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
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
                Mockito.anyInt()
                );
        Assertions.assertThrows(ProductNotFoundException.class, (()->ProductService.getProduct(1)));
    }

    //@Test
    void testGetByIdProductFound() throws ProductNotFoundException {
        var productExpected = new TppProduct();
        Mockito.doReturn(Optional.of(productExpected)).when(mockProductRepo).findById(
                Mockito.anyInt()
        );
        Assertions.assertEquals(productExpected, ProductService.getProduct(1));
    }

    //@Test
    void testNewProduct() {
        var productExpected = new TppProduct();
        var random = new Random();
        productExpected.setId(1)
                        .setClientId(BigInteger.valueOf(random.nextLong()))
                        .setNumber(String.valueOf(random.nextInt()))

                        .setDateOfConclusion(Timestamp.valueOf("2024-01-01 00:00:00"))
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