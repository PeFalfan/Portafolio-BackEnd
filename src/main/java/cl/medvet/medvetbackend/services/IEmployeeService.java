package cl.medvet.medvetbackend.services;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.models.ResponseModel;

public interface IEmployeeService {

    public ResponseModel getEmployees();

    public ResponseModel getEmployeeByRut(String rut);

    public ResponseModel deleteEmployee(String rut);

    public ResponseModel editEmployee(EmployeeModel emp);

    public ResponseModel createEmployee(EmployeeModel emp);
}
