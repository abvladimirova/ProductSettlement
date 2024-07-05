package app.dictionaries.accounttype;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tpp_ref_account_type")

public class AccountType {
    @Column(name = "internal_id")
    private int id;

    @Id
    @Column(name = "value")
    private String value;




}
