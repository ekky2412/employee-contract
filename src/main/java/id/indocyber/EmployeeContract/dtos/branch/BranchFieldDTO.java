package id.indocyber.EmployeeContract.dtos.branch;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BranchFieldDTO {
    private String code;
    private String name;
}
