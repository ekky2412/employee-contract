package id.indocyber.EmployeeContract.validators.annotations;

import id.indocyber.EmployeeContract.validators.validatorclass.IsIdExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})                        //tentuin dia bisa di pake dimananya
@Retention(RetentionPolicy.RUNTIME)                 //harus runtime
@Constraint(validatedBy = IsIdExistValidator.class)
public @interface IsIdExists {
    String message() default "Code or Id already exist. Please use another code";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
    Class<?> entityClass();
}
