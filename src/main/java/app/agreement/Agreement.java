package app.agreement;

import app.product.TppProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "agreement")
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}) // TODO проверить связь
    private TppProduct product;

    @Column(name = "general_agreement_id")
    private String generalAgreementId;

    @Column(name = "supplementary_agreement_id")
    private String supplementaryAgreementId;

    @Column(name = "arrangement_type")
    private String arrangementType;

    @Column(name = "sheduler_job_id")
    private BigInteger shedulerJobId;

    @Column(name = "number")
    private String number;

    @Column(name = "opening_date")
    private Date openingDate;

    @Column(name = "closing_date")
    private Date closingDate;

    @Column(name = "cancel_date")
    private Date cancelDate;

    @Column(name = "validity_duration")
    private BigInteger validityDuration;

    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @Column(name = "status")
    private String status;

    @Column(name = "interest_calculation_date")
    private Date interestCalculationDate;

    @Column(name = "interest_rate")
    private float interestRate;

    @Column(name = "coefficient")
    private float coefficient;

    @Column(name = "coefficient_action")
    private String coefficientAction;

    @Column(name = "minimum_interest_rate")
    private float minimumInterestRate;

    @Column(name = "minimum_interest_rate_coefficient")
    private float minimumInterestRateCoefficient;

    @Column(name = "minimum_interest_rate_coefficient_action")
    private String minimumInterestRateCoefficientAction;

    @Column(name = "maximal_interest_rate")
    private float maximalInterestRate;

    @Column(name = "maximal_interest_rate_coefficient")
    private float maximalInterestRateCoefficient;

    @Column(name = "maximal_interest_rate_coefficient_action")
    private String maximalInterestRateCoefficientAction;

}
