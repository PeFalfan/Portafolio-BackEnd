package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.BookTimeModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.BookServiceImpl;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/bookATime")
    public @ResponseBody ResponseModel bookATime(@RequestBody BookTimeModel book){
        ResponseModel response = new ResponseModel();

        try{
            response = bookService.bookATime(book);
        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping(value = "/getBookTimesByClient{client}")
    public @ResponseBody ResponseModel getBookTimesByClient(String client){

        ResponseModel response = new ResponseModel();
        try {
            response = bookService.getAllBookedTimesByClient(client);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }


}
