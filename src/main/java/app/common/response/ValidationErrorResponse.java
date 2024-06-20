package app.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import app.common.exceptions.Violation;

import java.util.List;
@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
