package id.indocyber.EmployeeContract.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contractStartDate", nullable = false)
    private LocalDate contractStartDate;

    @Column(name = "contractEndDate", nullable = false)
    private LocalDate contractEndDate;

    @ManyToOne
    @JoinColumn(name = "branchCode", nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "positionCode", nullable = false)
    private Position position;
}
