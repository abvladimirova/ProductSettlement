package app.product.api;

import app.account.AccountService;
import app.agreement.Agreement;
import app.agreement.AgreementRepo;
import app.common.exceptions.DuplicateRecordException;
import app.dictionaries.accounttype.AccountType;
import app.dictionaries.accounttype.AccountTypeService;
import app.dictionaries.productclass.ProductClass;
import app.dictionaries.productclass.ProductClassService;
import app.dictionaries.registertype.ProductRegisterType;
import app.dictionaries.registertype.RegisterTypeService;
import app.product.TppProduct;
import app.product.TppProductService;
import app.productregister.TppProductRegister;
import app.productregister.TppProductRegisterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ProductApiImpl implements ProductApi {

    private final TppProductService productService;
    private final RegisterTypeService registerTypeService;
    private final AgreementRepo agreementRepo;
    private final ProductClassService productClassService;
    private final AccountTypeService accountTypeService;
    private final AccountService accountService;
    private final TppProductRegisterService productRegisterService;
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public ProductApiImpl(TppProductService productService, RegisterTypeService registerTypeService, AgreementRepo agreementRepo, ProductClassService productClassService, AccountTypeService accountTypeService, AccountService accountService, TppProductRegisterService productRegisterService) {
        this.productService = productService;
        this.registerTypeService = registerTypeService;
        this.agreementRepo = agreementRepo;
        this.productClassService = productClassService;
        this.accountTypeService = accountTypeService;
        this.accountService = accountService;
        this.productRegisterService = productRegisterService;
    }

    private Date parseDate(String date, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest request) {
        final TppProduct product;
        List<TppProductRegister> productRegisters = new ArrayList<>();

        // 1. Если InstanceId не задан - создаем ЭП, если задан - только изменяем состав ДС
        if (Objects.isNull(request.getInstanceId())) {

            // 2. Проверка таблицы ЭП на дубли по ContractNumber
            TppProduct tmpProduct = productService.getProductByContractNumber(request.getContractNumber());
            if (!Objects.isNull(tmpProduct)) {
                throw new DuplicateRecordException("Параметр ContractNumber №" + request.getContractNumber() + " уже существует для ЭП с ИД " + tmpProduct.getId());
            }

            // 3. Проверка в Каталоге продуктов по коду
            ProductClass productClass = productClassService.findByCode(request.getProductCode());
            AccountType accountType = accountTypeService.getAccountType("Клиентский");

            // 5. Создание Экземпляра Продукта
            product = productService.newProduct(
                    productClass,
                    BigInteger.valueOf(Long.parseLong(request.getMdmCode())),
                    request.getProductType(),
                    request.getContractNumber(),
                    BigInteger.valueOf(request.getPriority()),
                    parseDate(request.getContractDate(), DATE_FORMAT),
                    request.getInterestRatePenalty(),
                    request.getMinimalBalance(),
                    request.getThresholdAmount(),
                    request.getAccountingDetails(),
                    request.getRateType(),
                    request.getTaxPercentageRate()
            );

            // 4. Создание первоначальных продуктовых регистров
            if (!(  request.getIsoCurrencyCode() == null    ||
                    request.getBranchCode() == null         ||
                    request.getUrgencyCode() == null        ))
            {

                List<ProductRegisterType> productRegisterTypes = registerTypeService.findByClassAndAccountType(productClass, accountType);
                if (!Objects.isNull(productRegisterTypes)) {
                    for (ProductRegisterType registerType : productRegisterTypes) {
                        var account = accountService.getAccountByParams(request.getBranchCode(),
                                request.getIsoCurrencyCode(),
                                request.getMdmCode(),
                                request.getUrgencyCode(),
                                registerType.getCode());
                        var productRegister = productRegisterService.newProductRegister(product.getId(), registerType, request.getIsoCurrencyCode(), account);
                        productRegisters.add(productRegister);
                    }
                }
            }

        }
        else {
            product = productService.getProduct(request.getInstanceId());
        }

        List<Agreement> agreements = request.getInstanceArrangement();
        if (!Objects.isNull(agreements) && !agreements.isEmpty()) {
            for (Agreement agreement : agreements) {
                Agreement tmpAgreement = agreementRepo.findByNumber(agreement.getNumber());
                if (!Objects.isNull(tmpAgreement)) {
                    throw new DuplicateRecordException("Параметр Дополнительного соглашения (сделки) Number "
                            + agreement.getNumber()
                            + " уже существует для ЭП с ИД " + tmpAgreement.getProduct().getId());
                }
                else
                {
                    agreement.setProduct(product);
                }
            }
            agreementRepo.saveAll(agreements);
        }

        return new CreateProductResponse(product, productRegisters, agreements);
    }
}
