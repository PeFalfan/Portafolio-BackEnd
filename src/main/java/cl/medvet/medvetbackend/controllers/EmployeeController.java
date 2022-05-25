package cl.medvet.medvetbackend.controllers;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService = new EmployeeServiceImpl();


    // Endpoint for get All Employees
    @GetMapping(value = "/getEmployees")
    public @ResponseBody ResponseModel getAllEmployees(){

        ResponseModel response = new ResponseModel();

        try{

            response = employeeService.getEmployees();

        }catch (Exception e){
            System.out.println("Error al consultar Servicio...");
            e.printStackTrace();
        }

        return response;
    }

    // Endpoint for get an Employee by rut
    @GetMapping(value = "/getEmployeeByRut{rutEmployee}")
    public @ResponseBody ResponseModel getEmployeeByRut(@RequestParam(value = "rutEmployee") String rutEmployee){

        ResponseModel response = new ResponseModel();

        try {

            response = employeeService.getEmployeeByRut(rutEmployee);

        } catch (Exception e){

            System.out.println("Error al consultar el servicio...");
            e.printStackTrace();

        }

        return response;

    }

    // Endpoint for deleting an employee
    @DeleteMapping(value = "/deleteEmployee{rutEmployee}")
    public @ResponseBody ResponseModel deleteEmployee(@RequestParam(value = "rutEmployee") String rutEmployee) {

        ResponseModel response = new ResponseModel();

        try {

            response = employeeService.deleteEmployee(rutEmployee);

        } catch (Exception e) {

            System.out.println("Error al eliminar el empleado...");
            e.printStackTrace();

        }

        return response;

    }

    // Enpoint for updating an employee
    @PutMapping(value = "/updateEmployee")
    public @ResponseBody ResponseModel updateEmployee(@RequestBody EmployeeModel employee) {
        ResponseModel response = new ResponseModel();

        try {

            response = employeeService.editEmployee(employee);

        }catch (Exception e) {
            System.out.println("Error al editar Empleado...");
            e.printStackTrace();
        }

        return response;
    }

    // Endpoint for creating an employee
    @PostMapping(value = "/createEmployee")
    public @ResponseBody ResponseModel createEmployee(@RequestBody EmployeeModel employee) {
        ResponseModel response = new ResponseModel();

        try {

            response = employeeService.createEmployee(employee);

        } catch (Exception e ){
            System.out.println("Error al crear empleado...");
            e.printStackTrace();
        }

        return  response;
    }

}
