package app.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private List<Error> errorList;
}
