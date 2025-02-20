package id.indocyber.EmployeeContract.dtos.position;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PositionFieldDTO {
    private String code;
    private String name;
}
