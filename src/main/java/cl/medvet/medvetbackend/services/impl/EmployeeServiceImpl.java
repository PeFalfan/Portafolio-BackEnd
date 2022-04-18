package cl.medvet.medvetbackend.services.impl;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.models.ResponseModel;
import cl.medvet.medvetbackend.repository.impl.EmployeeRepositoryImpl;
import cl.medvet.medvetbackend.services.IEmployeeService;
import cl.medvet.medvetbackend.util.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        try {

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

            // encoding pass:
            employee.setPassword(pe.encode(employee.getPassword()));

            resp = empRepo.createEmployee(employee);

            response.setData(resp);
            if (resp == 1) {
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

}
