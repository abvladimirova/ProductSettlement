package app.account.exceptions;

import lombok.Getter;

@Getter
public class AccountPoolNotFound extends Exception{
    private final String code = "ACCOUNTPOOL_NOT_FOUND";
    public AccountPoolNotFound(String message) {
        super(message);
    }
}
