package id.indocyber.EmployeeContract.services;

import id.indocyber.EmployeeContract.dtos.branch.BranchFieldDTO;
import id.indocyber.EmployeeContract.dtos.branch.BranchFormDTO;
import id.indocyber.EmployeeContract.models.Branch;
import id.indocyber.EmployeeContract.repositories.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    private BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchFieldDTO> get(){
        return branchRepository.findAll().stream().map(
                branch -> BranchFieldDTO.builder()
                        .code(branch.getCode())
                        .name(branch.getName())
                        .build()
        ).toList();
    }

    public BranchFieldDTO getByCode(String code){
        var branch = branchRepository.findById(code);
        if(branch.isPresent())
            return BranchFieldDTO.builder()
                    .code(branch.get().getCode())
                    .name(branch.get().getName())
                    .build();

        return BranchFieldDTO.builder().build();
    }

    public Boolean upsert(BranchFormDTO dto){
        var branch = Branch.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .build();
        try{
            branchRepository.save(branch);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Boolean delete(BranchFormDTO dto){
        try{
            branchRepository.deleteById(dto.getCode());
            return true;
        }catch (Exception e){
            System.err.println("Error : " + e.getMessage());
            return false;
        }
    }
}
