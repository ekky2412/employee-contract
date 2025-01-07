package id.indocyber.EmployeeContract.repositories;

import id.indocyber.EmployeeContract.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, String> {
    @Query("""
            SELECT p from Position p
            WHERE p.name = :name
            """)
    public Optional<Position> findByName(
            @Param("name") String name
    );
}
