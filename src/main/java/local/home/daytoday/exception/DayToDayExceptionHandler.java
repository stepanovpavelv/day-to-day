package local.home.daytoday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class DayToDayExceptionHandler {

    @ExceptionHandler(value = LinkException.class)
    public ProblemDetail handleException(LinkException ex) {
        return generateProblemDetails(ex.getStatus(), ex);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ProblemDetail handleSqlException(SQLIntegrityConstraintViolationException ex) {
        return generateProblemDetails(HttpStatus.FORBIDDEN, ex);
    }

    private static ProblemDetail generateProblemDetails(HttpStatus status, Throwable e) {
        var problems = ProblemDetail.forStatus(status);
        problems.setDetail(e.getLocalizedMessage());
        return problems;
    }
}