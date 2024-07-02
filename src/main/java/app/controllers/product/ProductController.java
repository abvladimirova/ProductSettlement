package app.controllers.product;

import app.productregister.exceptions.DuplicateRecordException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import app.product.entity.Agreement;
import app.product.exceptions.ProductNotFoundException;
import app.product.repository.AgreementRepo;
import app.product.entity.TppProduct;
import app.product.service.TppProductService;
import java.math.BigInteger;
import java.util.Objects;

@RestController
public class ProductController {

    private final TppProductService productService;
    private final AgreementRepo agreementRepo;
    @Autowired
    public ProductController(TppProductService productService, AgreementRepo agreementRepo) {
        this.productService = productService;
        this.agreementRepo = agreementRepo;
    }

    @PostMapping(value = "/corporate-settlement-instance/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TppProduct> createProduct(@RequestBody final @Valid CreateProductRequest request) throws ProductNotFoundException
    {
        final TppProduct product;
        if (Objects.isNull(request.getInstanceId())) {

            TppProduct tmpProduct = productService.getProductByContractNumber(request.getContractNumber());
            if (!Objects.isNull(tmpProduct)) {
                throw new DuplicateRecordException("Параметр ContractNumber №" + request.getContractNumber() + " уже существует для ЭП с ИД " + tmpProduct.getId());
            }

            product = productService.newProduct(
                    BigInteger.valueOf(Long.parseLong(request.getProductCode())),
                    BigInteger.valueOf(Long.parseLong(request.getMdmCode())),
                    request.getProductType(),
                    request.getContractNumber(),
                    BigInteger.valueOf(request.getPriority()),
                    request.getContractDate(),
                    request.getInterestRatePenalty(),
                    request.getMinimalBalance(),
                    request.getThresholdAmount(),
                    request.getRateType()/*,
                request.getAdditionalProperties().getData()*/

            );

        }
        else {
            product = productService.getProduct(request.getInstanceId());
        }
        /*var agreements = request.getInstanceArrangement();
        for (Agreement agreement : agreements) {
            agreement.setProduct(product);
        }
        agreementRepo.saveAll(agreements);*/

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                //.contentType(MediaType.APPLICATION_JSON)
                .body(product);
    }

}
