package app.registertype.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tpp_ref_product_register_type")
@Table(name = "tpp_ref_product_register_type")
@ToString
public class ProductRegisterType {
    @Column(name="internal_id")
    private int id;

    @Id
    @Column(name = "value")
    private String code;

    @Column(name = "register_type_name")
    private String name;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) // TODO проверить связь
    @JoinColumn(name = "product_class_code")
    private ProductClass productClass;*/

    @Column(name = "register_type_start_date")
    private Timestamp startDate;

    @Column(name = "register_type_end_date")
    private Timestamp endDate;

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) // TODO проверить связь
    @JoinColumn(name = "account_type")
    private AccountType accountType;*/
}
