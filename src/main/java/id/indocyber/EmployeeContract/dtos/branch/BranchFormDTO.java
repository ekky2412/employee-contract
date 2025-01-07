package id.indocyber.EmployeeContract.dtos.branch;

import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.validators.annotations.IsIdExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BranchFormDTO {
    @IsIdExists(entityClass = Branch.class)
    private String code;
    @NotNull
    @NotBlank
    private String name;
    private Boolean isEdit;
}
