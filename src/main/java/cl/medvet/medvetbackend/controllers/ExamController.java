package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ExamModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ExamController {

    @Autowired
    ExamServiceImpl examService = new ExamServiceImpl();

    @GetMapping(value = "/getExamsFor{idPet}")
    public @ResponseBody ResponseModel getExamsFor(@RequestParam(value = "idPet") int idPet){
        ResponseModel response = new ResponseModel();

        try{
            response = examService.getExamsByPet(idPet);
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @PostMapping(value = "/uploadExam")
    public @ResponseBody ResponseModel uploadExam(@RequestBody ExamModel exam){
        ResponseModel response = new ResponseModel();

        try {
            response = examService.uploadExam(exam);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
