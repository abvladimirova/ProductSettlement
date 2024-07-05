package app.dictionaries.productclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tpp_ref_product_class")

public class ProductClass {

    @Column(name = "internal_id")
    private int id;

    @Id
    @Column(name = "value")
    @NonNull
    private String value;

    @Column(name = "gbi_code")
    private String gbiCode;

    @Column(name = "gbi_name")

    private String gbiName;

    @Column(name = "product_row_code")
    private String rowCode;

    @Column(name = "product_row_name")
    private String rowName;

    @Column(name = "subclass_code")
    private String subclassCode;

    @Column(name = "subclass_name")
    private String subclassName;
}
