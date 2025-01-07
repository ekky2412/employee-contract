package id.indocyber.EmployeeContract.dtos.employees;

import id.indocyber.EmployeeContract.dtos.branch.BranchFieldDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFieldDTO;
import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.models.Employee;
import id.indocyber.EmployeeContract.models.Position;
import id.indocyber.EmployeeContract.validators.annotations.IsIdExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
public class EmployeeFormDTO {
    @NotBlank
    @IsIdExists(entityClass = Employee.class)
    private String employeeCode;
    @NotBlank
    private String employeeName;
    @NotBlank
    private String branchCode;
    @NotBlank
    private String positionCode;
    @NotNull
    private LocalDate contractStartDate;
    @NotNull
    private LocalDate contractEndDate;
    private Boolean isEdit;
}
