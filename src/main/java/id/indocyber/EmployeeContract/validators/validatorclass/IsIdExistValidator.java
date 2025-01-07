package id.indocyber.EmployeeContract.validators.validatorclass;

import id.indocyber.EmployeeContract.repositories.BranchRepository;
import id.indocyber.EmployeeContract.repositories.EmployeeRepository;
import id.indocyber.EmployeeContract.repositories.PositionRepository;
import id.indocyber.EmployeeContract.validators.annotations.IsIdExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsIdExistValidator implements ConstraintValidator<IsIdExists, String> {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    private Class<?> entityClass;

    @Override
    public void initialize(IsIdExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entityClass = constraintAnnotation.entityClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return false;
        }

        return switch (this.entityClass.getSimpleName()) {
            case "Employee" -> !employeeRepository.existsById(value);
            case "Branch" -> !branchRepository.existsById(value);
            case "Position" -> !positionRepository.existsById(value);
            default -> true;
        };
    }

}
