import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUrlValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUrl {
    String message() default "{url.unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
