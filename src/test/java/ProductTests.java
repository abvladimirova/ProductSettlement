import app.Main;
import app.agreement.AgreementRepo;
import app.dictionaries.productclass.ProductClass;
import app.product.TppProduct;
import app.product.TppProductRepo;
import app.product.TppProductService;
import app.product.api.CreateProductRequest;
import app.product.api.CreateProductResponse;
import app.product.api.ProductApi;
import app.productregister.TppProductRegister;
import app.productregister.TppProductRegisterRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = Main.class)
@Testcontainers
@AutoConfigureMockMvc
public class ProductTests {
    private final TppProductRegisterRepo tppProductRegisterRepo;
    private final TppProductRepo tppProductRepo;
    private final AgreementRepo agreementRepo;
    private final ProductApi productApi;

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.3"));

    @Autowired
    MockMvc mvc;

    @Spy
    private TppProductService spyProductService;

    @Autowired
    public ProductTests(TppProductRegisterRepo tppProductRegisterRepo, TppProductRepo tppProductRepo, AgreementRepo agreementRepo, ProductApi productApi) {
        this.tppProductRegisterRepo = tppProductRegisterRepo;
        this.tppProductRepo = tppProductRepo;
        this.agreementRepo = agreementRepo;
        this.productApi = productApi;
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.datasource.username", postgresContainer::getUsername);

        registry.add("spring.liquibase.url", postgresContainer::getJdbcUrl);
        registry.add("spring.liquibase.user", postgresContainer::getUsername);
        registry.add("spring.liquibase.password", postgresContainer::getPassword);
        registry.add("spring.liquibase.enabled=", () -> true);
        registry.add("spring.liquibase.change-log=", () -> "classpath:/db/changelog/db.changelog-master.yaml");
    }

    @BeforeEach
    public void initData() {
        tppProductRegisterRepo.deleteAll();
        tppProductRepo.deleteAll();
        agreementRepo.deleteAll();
    }

    CreateProductRequest createNormalRequest() {
        return CreateProductRequest.builder()
                .productType("договор")
                .productCode("03.012.002")
                .registerType("type")
                .mdmCode("15")
                .contractNumber("1")
                .contractDate("2021-01-01")
                .priority(1)
                .branchCode("0022")
                .isoCurrencyCode("800")
                .urgencyCode("00")
                .minimalBalance(BigDecimal.valueOf(10000L))
                .thresholdAmount(BigDecimal.valueOf(100L))
                .interestRatePenalty(5)
                .taxPercentageRate(1)
                .rateType("1")
                .accountingDetails("details")
                .build();
    }

    @Test
    public void testNewProductSuccess(){
        CreateProductRequest testRequest = createNormalRequest();

        assertDoesNotThrow(() -> productApi.createProduct(testRequest));
        assertEquals(1,tppProductRepo.count());
        assertEquals(1,tppProductRegisterRepo.count());
        assertEquals(0,agreementRepo.count());

        TppProduct product = tppProductRepo.findAll().get(0);

        assertEquals("1",product.getNumber());
        assertEquals("договор",product.getProductType());
        assertEquals(BigInteger.valueOf(15L),product.getClientId());
        assertEquals(Date.valueOf("2021-01-01"),product.getDateOfConclusion());
        assertEquals("Открыт", product.getState());

    }

    @Test
    public void testNewProductRequestSuccess() throws Exception {

        assertEquals(0,tppProductRepo.count());
        CreateProductRequest testRequest = createNormalRequest();

        MvcResult result = mvc.perform(post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testRequest)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(ACCEPTED.value(),response.getStatus());

        List<TppProduct> products = tppProductRepo.findAll();
        List<TppProductRegister> registers = tppProductRegisterRepo.findAll();

        assertEquals(1,products.size());
        assertEquals(1,registers.size());
        assertEquals(0,agreementRepo.count());

        CreateProductResponse productResponse = new ObjectMapper().readValue(response.getContentAsString(), CreateProductResponse.class);

        assertEquals(products.get(0).getId().toString(), productResponse.getInstanceId());
        assertEquals(registers.get(0).getId(), productResponse.getRegisterId().get(0));
        assertEquals(0, productResponse.getSupplementaryAgreementId().size());

    }

       @Test
        public void testNewProductRequestBadRequest() throws Exception {
            CreateProductRequest testRequest = createNormalRequest();
            testRequest.setContractNumber("");

            MvcResult result = mvc.perform(post("/corporate-settlement-instance/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(testRequest)))
                            .andReturn();

            MockHttpServletResponse response = result.getResponse();

            assertEquals(BAD_REQUEST.value(),response.getStatus());
            assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains("Значение обязательного параметра contractNumber не заполнено"));

            assertEquals(0,tppProductRepo.count());
            assertEquals(0,tppProductRegisterRepo.count());
            assertEquals(0,agreementRepo.count());
            verify(spyProductService, times(0)).newProduct(any(ProductClass.class),
                   any(BigInteger.class),
                   anyString(),
                   anyString(),
                   any(BigInteger.class),
                   any(java.util.Date.class),
                   anyFloat(),
                   any(BigDecimal.class),
                   any(BigDecimal.class),
                   anyString(),
                   anyString(),
                   anyFloat());
        }

    @Test
    public void testNewProductDublicateContractNumber() throws Exception {
        CreateProductRequest testRequest = createNormalRequest();

        // создается первый договор
        assertDoesNotThrow(() -> productApi.createProduct(testRequest));
        assertEquals(1,tppProductRepo.count());
        assertEquals("1",tppProductRepo.findAll().get(0).getNumber());

        // создание дубля:
        MvcResult result = mvc.perform(post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testRequest)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(BAD_REQUEST.value(),response.getStatus());
        assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains("Параметр ContractNumber №1 уже существует для ЭП с ИД "));
        assertEquals(1,tppProductRepo.count());
    }

    @Test
    public void testNewProductNotFound() throws Exception {
        CreateProductRequest testRequest = createNormalRequest();
        testRequest.setProductCode("TEST"); // не существующий код продукта

        // создание дубля:
        MvcResult result = mvc.perform(post("/corporate-settlement-instance/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(testRequest)))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(NOT_FOUND.value(),response.getStatus());
        assertTrue(response.getContentAsString(StandardCharsets.UTF_8).contains("Код Продукта TEST не найден в Каталоге продуктов"));
        assertEquals(0,tppProductRepo.count());
    }

}