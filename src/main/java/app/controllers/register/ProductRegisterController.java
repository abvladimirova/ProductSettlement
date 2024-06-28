package app.controllers.register;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import app.account.exceptions.AccountNotFoundException;
import app.account.exceptions.AccountPoolNotFound;
import app.productregister.exceptions.DuplicateRecordException;
import app.productregister.entity.TppProductRegister;
import app.productregister.service.TppProductRegisterService;
import app.registertype.exceptions.RegisterTypeNotFoundException;
import app.registertype.service.RegisterTypeService;
import app.account.service.AccountService;
import app.product.exceptions.ProductNotFoundException;

import java.util.*;

@RestController
public class ProductRegisterController {

    private final AccountService accountService;
    private final TppProductRegisterService productRegisterService;
    private final RegisterTypeService typeService;

    @Autowired
    public ProductRegisterController(AccountService accountService,
                                     TppProductRegisterService productRegisterService,
                                     RegisterTypeService typeService) {
        this.accountService = accountService;
        this.productRegisterService = productRegisterService;
        this.typeService = typeService;
    }

    @PostMapping (value = "/corporate-settlement-account/create", consumes = "application/json", produces = "application/json")
    //@Transactional
    public ResponseEntity<TppProductRegister> createProductRegister(@Valid @RequestBody final CreateProductRegisterRequest request)
            throws RegisterTypeNotFoundException, AccountNotFoundException, AccountPoolNotFound, ProductNotFoundException
    {

        // 2. - Найти Request.Body.registryTypeCode в tpp_ref_product_register_type.value
        var type = typeService.findByCode(request.getRegistryTypeCode());

        // 3. Проверка таблицы ПР на дубли
        TppProductRegister r = productRegisterService.getProductRegister(request.getInstanceId(), type);
        if (!Objects.isNull(r)) {
            throw new DuplicateRecordException("Параметр registryTypeCode тип регистра " + request.getRegistryTypeCode() + " уже существует для ЭП с ИД " + request.getInstanceId());
        }

        // 4. Выбор счёта
        var account = accountService.getAccountByParams(request.getBranchCode(),
                request.getCurrencyCode(),
                request.getMdmCode(),
                request.getPriorityCode(),
                request.getRegistryTypeCode());

        //Шаг 5 - Сформировать новый продуктовый регистр и записать его в БД
        var productRegister = productRegisterService.newProductRegister(request.getInstanceId(),type,request.getCurrencyCode(), account);

        //var response = new CreateProductRegisterResponse(new CreateProductRegisterResponseData(String.valueOf(productRegister.getId())));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                //.contentType(MediaType.APPLICATION_JSON)
                .body(productRegister);

    }

}


