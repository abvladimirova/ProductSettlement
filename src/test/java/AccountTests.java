import app.common.exceptions.NoDataFoundException;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import app.Main;
import app.account.Account;
import app.dictionaries.accountpool.AccountPool;
import app.dictionaries.accountpool.AccountPoolRepo;
import app.account.AccountRepo;
import app.account.AccountServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class AccountTests {

    @Mock
    public AccountPoolRepo mockAccountPoolRepo;
    @Mock
    public AccountRepo mockAccountRepo;

    @InjectMocks
    AccountServiceImpl accountService;

    //@Test
    void testAccountPoolNotFound(){
        Mockito.doReturn(null).when(mockAccountPoolRepo).findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString());

        assertThrows(NoDataFoundException.class,
                () -> accountService.getAccountByParams(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString()));
    }
    //@Test
    void testAccountNotFound(){
        Mockito.doReturn(new AccountPool()).when(mockAccountPoolRepo).findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString());
        Mockito.doReturn(null).when(mockAccountRepo).findFirstByPoolAndBusyFalseOrderByAccountNumber(
                Mockito.any(AccountPool.class));
        assertThrows(NoDataFoundException.class,(() -> accountService.getAccountByParams(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString())));
    }

    //@Test
    void testAccountFoundSuccess() throws NoDataFoundException {
        var pool = new AccountPool();
        Mockito.doReturn(pool).when(mockAccountPoolRepo).findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString());
        var accountExpect = new Account("1234567890", pool);

        Mockito.doReturn(accountExpect).when(mockAccountRepo).findFirstByPoolAndBusyFalseOrderByAccountNumber(
                Mockito.any(AccountPool.class));

        Assertions.assertEquals(accountExpect, accountService.getAccountByParams(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString()));
    }
}