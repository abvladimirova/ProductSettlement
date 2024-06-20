package app.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import app.common.exceptions.Violation;



@Data
@AllArgsConstructor
public class BusinessErrorResponse {
    private final Violation error;

    public BusinessErrorResponse(String fieldName, String error) {
        this.error = new Violation(fieldName,error);
    }
}
;
