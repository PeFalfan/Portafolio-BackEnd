package cl.medvet.medvetbackend.repository;

import cl.medvet.medvetbackend.models.EmployeeModel;

import java.util.List;

public interface IEmployeeRepository {

    public List<EmployeeModel> getAllEmployees();

    public EmployeeModel getEmployeeByRut(String rut);

    public int deleteEmployee(String rut);

    public int editEmployee(EmployeeModel employee);

    public int createEmployee(EmployeeModel employee);
}
