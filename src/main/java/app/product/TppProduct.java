package app.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity(name = "tpp_product")
@Accessors(chain = true)
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "product_code_id")
    private BigInteger productCodeId;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "type")
    private String productType;

    @Column(name = "number")
    private String number;

    @Column(name = "priority")
    private BigInteger priority;

    @Column(name = "date_of_conclusion")
    private Date dateOfConclusion;

    @Column(name = "start_date_time")
    private Timestamp startDateTime;

    @Column(name = "end_date_time")
    private Timestamp endDateTime;

    @Column(name = "days")
    private BigInteger days;

    @Column(name = "penalty_rate")
    private float penaltyRate;

    @Column(name = "nso")
    private BigDecimal nso;

    @Column(name = "threshold_amount")
    private BigDecimal thresholdAmount;

    @Column(name = "requisite_type")
    private String requisiteType;

    @Column(name = "interest_rate_type")
    private String interestRateType;

    @Column(name = "tax_rate")
    private float taxRate;

    @Column(name = "reasone_close")
    private String reasonClose;

    @Column(name = "state")
    private String state;

}
