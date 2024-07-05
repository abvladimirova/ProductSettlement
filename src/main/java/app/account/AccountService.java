package app.account;

import app.common.exceptions.NoDataFoundException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    Account getAccountByParams(
            @NotBlank String branch,
            @NotBlank String currency,
            @NotBlank String mdmCode,
            @NotBlank String priority,
            @NotBlank String registryType) throws NoDataFoundException
            ;
    Account getAccountByParams(
            @NotBlank String branch,
            @NotBlank String currency,
            @NotBlank String mdmCode,
            @NotBlank String priority,
            @NotBlank String registryType,
            boolean throwError);

}
