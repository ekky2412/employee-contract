package id.indocyber.EmployeeContract.controllers;

import id.indocyber.EmployeeContract.dtos.position.PositionFormDTO;
import id.indocyber.EmployeeContract.services.PositionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/position")
public class PositionController {
    private PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("")
    public ModelAndView index(){
        return new ModelAndView("position/index")
                .addObject("page", "position")
                .addObject("positions", positionService.get());
    }

    @GetMapping("/insert")
    public ModelAndView insert(){
        return new ModelAndView("position/form")
                .addObject("page", "position")
                .addObject("position", PositionFormDTO.builder().isEdit(false).build());
    }

    @GetMapping("/edit")
    public ModelAndView update(String code){
        return new ModelAndView("position/form")
                .addObject("page", "position")
                .addObject("formEdit", true)
                .addObject("position", positionService.getByCode(code));
    }

    @PostMapping("/upsert")
    public ModelAndView upsert(@Valid @ModelAttribute PositionFormDTO dto, BindingResult bindingResult){
        if(bindingResult.hasErrors() && (dto.getIsEdit() == null || !dto.getIsEdit())){
            dto.setCode(null);
            return new ModelAndView("position/form")
                    .addObject("page", "position")
                    .addObject("bindingResult", bindingResult)
                    .addObject("position", dto);
        }

        boolean codeError = true;
        if(bindingResult.hasErrors()){
            for(var error : bindingResult.getFieldErrors()){
                System.out.println(error.getDefaultMessage());
                if(error.getDefaultMessage().equals("Code or Id already exist. Please use another code")){
                    codeError = false;
                    break;
                }
            }
        }

        if(bindingResult.hasErrors() && codeError){
            dto.setCode(null);
            return new ModelAndView("position/form")
                    .addObject("page", "position")
                    .addObject("bindingResult", bindingResult)
                    .addObject("formEdit", true)
                    .addObject("position", dto);
        }

        Boolean result = positionService.upsert(dto);
        String message = result ? "Data berhasil ditambahkan!" : "Terjadi kesalahan saat menyimpan data";
        return new ModelAndView("position/index")
                .addObject("page", "position")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("positions", positionService.get());
    }

    @GetMapping("/delete")
    public ModelAndView deleteConfirmation(String code){
        return new ModelAndView("position/delete-confirmation")
                .addObject("page", "position")
                .addObject("position", PositionFormDTO.builder()
                        .code(code)
                        .build());
    }

    @PostMapping("/delete")
    public ModelAndView delete(PositionFormDTO dto){
        Boolean result = positionService.delete(dto);
        String message = result ? "Data berhasil dihapus!" : "Terjadi kesalahan saat menghapus data";
        return new ModelAndView("position/index")
                .addObject("page", "position")
                .addObject("successUpsert", result)
                .addObject("message", message)
                .addObject("positions", positionService.get());
    }
}
