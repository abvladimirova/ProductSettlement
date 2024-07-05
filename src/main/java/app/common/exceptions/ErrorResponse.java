package app.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import app.common.exceptions.Error;

import java.util.List;
@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final List<Error> errorList;
}
