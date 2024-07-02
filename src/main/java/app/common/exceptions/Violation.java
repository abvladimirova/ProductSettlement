package app.common.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class Violation {
    private final String code;
    private final String message;
}
