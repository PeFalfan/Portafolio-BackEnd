package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ExamModel;
import cl.medvet.medvetbackend.models.PrescriptionModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.ExamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class ExamController {

    @Autowired
    ExamServiceImpl examService = new ExamServiceImpl();

    @GetMapping(value = "/getExamsFor{idPet}{email}")
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

    @PostMapping(value = "/uploadPrescription")
    public @ResponseBody ResponseModel uploadPrescription(@RequestBody PrescriptionModel prescription){
        ResponseModel response = new ResponseModel();

        try{
            response = examService.uploadPrescription(prescription);
        }catch (Exception e){
            e.printStackTrace();
        }

        return  response;
    }

    @GetMapping(value = "/sendExamsByIdPet{idPet}")
    public @ResponseBody ResponseModel sendExamsByIdPet(@RequestParam(value = "idPet") int idPet){
        ResponseModel response = new ResponseModel();
        try {
            response = examService.requestExamsResults(idPet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/requestPrescriptionsByPet{idPet}")
    public @ResponseBody ResponseModel requestPrescriptionsByPet(@RequestParam(value = "idPet") int idPet){
        ResponseModel response = new ResponseModel();

        try{
            response = examService.requestPresc(idPet);
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping(value = "/loadPrescriptionsByPet{idPet}")
    public @ResponseBody ResponseModel loadPrescriptionsByPet(@RequestParam(value = "idPet") int idPet){
        ResponseModel res = new ResponseModel();

        try{
            res = examService.loadPrescriptions(idPet);
        } catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
