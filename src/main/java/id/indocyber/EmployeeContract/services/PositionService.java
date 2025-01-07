package id.indocyber.EmployeeContract.services;

import id.indocyber.EmployeeContract.dtos.branch.BranchFieldDTO;
import id.indocyber.EmployeeContract.dtos.branch.BranchFormDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFieldDTO;
import id.indocyber.EmployeeContract.dtos.position.PositionFormDTO;
import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.models.Position;
import id.indocyber.EmployeeContract.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<PositionFieldDTO> get(){
        return positionRepository.findAll().stream().map(
                position -> PositionFieldDTO.builder()
                        .code(position.getCode())
                        .name(position.getName())
                        .build()
        ).toList();
    }

    public PositionFieldDTO getByCode(String code){
        var position = positionRepository.findById(code);
        if(position.isPresent())
            return PositionFieldDTO.builder()
                    .code(position.get().getCode())
                    .name(position.get().getName())
                    .build();

        return PositionFieldDTO.builder().build();
    }

    public Optional<Position> getByName(String name){
        return positionRepository.findByName(name);
    }

    public Boolean upsert(PositionFormDTO dto){
        var position = Position.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
        try{
            positionRepository.save(position);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Boolean delete(PositionFormDTO dto){
        try{
            positionRepository.deleteById(dto.getCode());
            return true;
        }catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }
    }
}
