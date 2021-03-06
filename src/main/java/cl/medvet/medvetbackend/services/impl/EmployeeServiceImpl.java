package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.EmployeeRepositoryImpl;
import cl.medvet.medvetbackend.services.IEmployeeService;
import cl.medvet.medvetbackend.util.EmailCommunication;
import cl.medvet.medvetbackend.util.PasswordEncryption;
import cl.medvet.medvetbackend.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepositoryImpl empRepo = new EmployeeRepositoryImpl();

    PasswordEncryption pe = new PasswordEncryption();

    @Override
    public ResponseModel getEmployees() {

        ResponseModel response = new ResponseModel();

        try{

            response.setData(empRepo.getAllEmployees());
            response.setMessageResponse("Lista cargada correctamente...");
            response.setError(null);

        }catch (Exception e){

            response.setMessageResponse("Error al cargar lista de empleados...");
            response.setData(null);
            response.setError(e.getMessage());
            e.printStackTrace();

        }

        return response;
    }

    @Override
    public ResponseModel getEmployeeByRut(String rut) {

        ResponseModel response = new ResponseModel();

        try{

            response.setData(empRepo.getEmployeeByRut(rut));

            if ( response.getData() != null ) {

                response.setMessageResponse("Empleado encontrado...");
                response.setError(null);

            } else {

                response.setError(null);
                response.setMessageResponse("Empleado no encontrado...");

            }

        }catch (Exception e){

            response.setData(null);
            response.setMessageResponse("Error al cargar empleado...");
            response.setError(e.getMessage());
            e.printStackTrace();

        }

        return response;
    }

    @Override
    public ResponseModel deleteEmployee(String rut) {

        ResponseModel response = new ResponseModel();

        try {

            int resp = (empRepo.deleteEmployee(rut));
            response.setData(resp);

            if ( resp == 1 ){

                response.setMessageResponse("Empleado eliminado correctamente...");
                response.setError(null);

            } else {

                response.setMessageResponse("Empleado no encontrado... ");
                response.setError("Empleado no encontrado en BD.");

            }

        } catch (Exception e) {

            e.printStackTrace();
            response.setError("Error de servicio...");
            response.setMessageResponse("Error al intentar eliminar al empleado...");
            response.setData(null);

        }

        return response;
    }

    @Override
    public ResponseModel editEmployee(EmployeeModel emp) {

        ResponseModel response = new ResponseModel();
        int res = 0;

        EmployeeModel controlEmployee = new EmployeeModel();

        controlEmployee = empRepo.getEmployeeByRut(emp.getRutEmployee());

        try {

            if (!emp.getPassword().equals(controlEmployee.getPassword())){
                emp.setPassword(pe.encode(emp.getPassword()));
            }

            res = empRepo.editEmployee(emp);

            response.setData(res);
            if (res == 1) {
                response.setMessageResponse("Empleado editado correctamente");
                response.setError(null);
            }else {
                response.setError("Error al intentar editar al empleado...");
                response.setMessageResponse("Error al intentar editar al empleado...");
            }

        }catch (Exception e){
            e.printStackTrace();
            response.setData(null);
            response.setError(e.getMessage());
            response.setMessageResponse("Error en servicio al actualizar empleado...");
        }
        return response;
    }

    @Override
    public ResponseModel createEmployee(EmployeeModel employee) {

        ResponseModel response = new ResponseModel();
        int resp = 0;

        try {

            employee.setPassword(PasswordGenerator.getAlphaNumericString());

            // encoding pass:
            employee.setPassword(pe.encode(employee.getPassword()));

            resp = empRepo.createEmployee(employee);

            response.setData(resp);
            if (resp == 1) {

                String bodyMessage = "Buenas tardes " + employee.getNameEmployee() + "!\n" +
                        "Esperando te encuentres bien, te queremos dar la bienvenida al equipo MEDVET! aqui tu clave de ingreso a nuestra plataforma!\n" +
                        "Clave: " + pe.decode(employee.getPassword()) +"\n" +
                        "crezcamos juntos!!\n" +
                        "Equipo MEDVET.";

                EmailCommunication.sendMail(employee.getEmailEmployee(), "Entrega Credenciales nuevo colaborador",bodyMessage, new ArrayList<>());

                response.setMessageResponse("Empleado creado correctamente...");
                response.setError(null);
            } else {
                response.setError("Error al crear empleado...");
                response.setMessageResponse("Empleado ya registrado...");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setError(e.getMessage());
            response.setMessageResponse("Error al crear Empleado.");
        }
        return response;
    }

    //TODO create a service to load the Specialties and type of employees
}
