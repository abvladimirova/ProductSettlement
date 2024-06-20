package app.productregister.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import app.product.entity.TppProduct;
import app.registertype.entity.ProductRegisterType;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tpp_product_register")
@Table(name="tpp_product_register")

public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // IDENTITY - запись создаётся, но приложение падает с ошибкой
    //com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
    // No serializer found for class task5.productregister.controller.CreateProductRegisterResponse
    // and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) // TODO проверить связь
    @JoinColumn(name = "product_id")
    private TppProduct product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) // TODO проверить связь
    @JoinColumn(name = "type")
    private ProductRegisterType type;

    @Column(name = "currency_code")
    private String currency;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account")
    private BigInteger account;

    @Column(name = "state")
    private String state;
}
