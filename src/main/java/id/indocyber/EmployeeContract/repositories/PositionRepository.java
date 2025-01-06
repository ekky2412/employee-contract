package id.indocyber.EmployeeContract.repositories;

import id.indocyber.EmployeeContract.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, String> {
}
