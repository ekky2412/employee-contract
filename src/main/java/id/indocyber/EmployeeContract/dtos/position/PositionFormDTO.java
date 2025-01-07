package id.indocyber.EmployeeContract.dtos.position;

import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.models.Position;
import id.indocyber.EmployeeContract.validators.annotations.IsIdExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PositionFormDTO {
    @IsIdExists(entityClass = Position.class)
    private String code;
    @NotNull
    @NotBlank
    private String name;
    private Boolean isEdit;
}
