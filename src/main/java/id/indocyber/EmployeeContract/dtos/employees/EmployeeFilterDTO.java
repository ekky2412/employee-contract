package id.indocyber.EmployeeContract.dtos.employees;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeFilterDTO {
    private Integer pageNumber;
    private Integer pageSize;
    private String employeeCode;
    private String branchCode;
    private String positionCode;
}
