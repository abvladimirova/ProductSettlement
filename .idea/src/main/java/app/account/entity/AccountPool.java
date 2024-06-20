package app.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="account_pool")
@Table(name="account_pool")
public class AccountPool {
    @Id
    private Integer id;

    @Column(name = "branch_code")
    private String branch;

    @Column(name = "currency_code")

    private String currency;

    @Column(name = "mdm_code")

    private String mdmCode;

    @Column(name = "priority_code")
    private String priority;

    @Column(name = "registry_type_code")
    private String registerType;
}
