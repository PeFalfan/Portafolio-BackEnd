package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.BookServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class bookTimeController {

    private final BookServiceImpl bookService = new BookServiceImpl();


    @GetMapping(value = "/getBookTimes")
    public @ResponseBody ResponseModel getBookTimes(){

        ResponseModel response = new ResponseModel();
        try {
            response = bookService.getAllBookTimes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = "/getBookTimesByPet{idPet}")
    public @ResponseBody ResponseModel getBookTimesByPet(int idPet){

        ResponseModel response = new ResponseModel();
        try {
            response = bookService.getAllBookedTimesByPet(idPet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }


}
