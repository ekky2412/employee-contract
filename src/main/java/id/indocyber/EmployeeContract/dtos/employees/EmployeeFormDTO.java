package id.indocyber.EmployeeContract.dtos.employees;

import id.indocyber.EmployeeContract.dtos.branch.BranchFieldDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFieldDTO;
import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.models.Position;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
public class EmployeeFormDTO {
    private String employeeCode;
    private String employeeName;
    private String branchCode;
    private String positionCode;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
}
