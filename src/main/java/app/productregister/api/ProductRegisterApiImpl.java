package app.productregister.api;

import app.account.AccountService;
import app.common.exceptions.DuplicateRecordException;
import app.dictionaries.registertype.RegisterTypeService;
import app.productregister.TppProductRegister;
import app.productregister.TppProductRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProductRegisterApiImpl implements ProductRegisterApi {
    private final AccountService accountService;
    private final TppProductRegisterService productRegisterService;
    private final RegisterTypeService typeService;

    @Autowired
    public ProductRegisterApiImpl(AccountService accountService, TppProductRegisterService productRegisterService, RegisterTypeService typeService) {
        this.accountService = accountService;
        this.productRegisterService = productRegisterService;
        this.typeService = typeService;
    }

    @Transactional
    public CreateProductRegisterResponse createProductRegister(@Valid CreateProductRegisterRequest request)
    {

        // 1. Найти Request.Body.registryTypeCode в tpp_ref_product_register_type.value
        var type = typeService.findByCode(request.getRegistryTypeCode());

        // 2. Проверка таблицы ПР на дубли
        TppProductRegister r = productRegisterService.getProductRegister(request.getInstanceId(), type);
        if (!Objects.isNull(r)) {
            throw new DuplicateRecordException("Параметр registryTypeCode тип регистра " + request.getRegistryTypeCode() + " уже существует для ЭП с ИД " + request.getInstanceId());
        }

        // 3. Выбор счёта
        var account = accountService.getAccountByParams(request.getBranchCode(),
                request.getCurrencyCode(),
                request.getMdmCode(),
                request.getPriorityCode(),
                request.getRegistryTypeCode());

        // 4. Сформировать новый продуктовый регистр и записать его в БД
        var productRegister = productRegisterService.newProductRegister(request.getInstanceId(),type,request.getCurrencyCode(), account);

        return new CreateProductRegisterResponse(productRegister);

    }
}
