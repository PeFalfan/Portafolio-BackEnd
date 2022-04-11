package cl.medvet.medvetbackend;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.repository.IEmployeeRepository;
import cl.medvet.medvetbackend.repository.impl.EmployeeRepositoryImpl;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class MedvetBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedvetBackEndApplication.class, args);

        try (Connection conn = DataBaseConnection.getConnection()){


            // test Lista completa

            IEmployeeRepository repo = new EmployeeRepositoryImpl();
            /*
            List<EmployeeModel> empleados = repo.getAllEmployees();

            //System.out.println("Nombre: " + empleados.get(0).getNameEmployee());

            // test busqueda individual
            EmployeeModel empleado = repo.getEmployeeByRut("19.237.143");

            if (empleado != null){
                System.out.println("Nombre: " + empleado.getNameEmployee());
            }else{
                System.out.println("eeeeh! titán, que no encontré el rut!");
            }

            // test eliminar empleado
            if (repo.deleteEmployee("19.237.143") == 0){
                System.out.println("Registro no encontrado en BD");
            }else{
                System.out.println("Registro eliminado de BD");
            }

            // test crear empleado:
            EmployeeModel emp = new EmployeeModel();
            emp.setRutEmployee("19.848.813");
            emp.setDvRut("3");
            emp.setNameEmployee("José Falfan");
            emp.setEmailEmployee("jose.falfan@gmail.com");
            emp.setSpeciality("Servicio de Cirugía de Tejidos Blandos");
            emp.setTypeEmployee(2);
            emp.setPassword("knuuto56");

            repo.createEmployee(emp);

            emp.setRutEmployee("19.237.143");
            emp.setDvRut("0");
            emp.setNameEmployee("Pedro Falfan");
            emp.setEmailEmployee("pete.falfan@gmail.com");
            emp.setSpeciality("Servicio de Cirugía de Tejidos Blandos");
            emp.setTypeEmployee(1);
            emp.setPassword("knuuto56");

            repo.createEmployee(emp);

             */

            // test editar empleado:

            EmployeeModel empployeeToEdit = new EmployeeModel();
            empployeeToEdit.setRutEmployee("19.237.143");
            empployeeToEdit.setDvRut("4");
            empployeeToEdit.setNameEmployee("Nombre editad 2o");
            empployeeToEdit.setEmailEmployee("emai32 l.com");
            empployeeToEdit.setSpeciality("Especiali23rdad editada");
            empployeeToEdit.setTypeEmployee(3);
            empployeeToEdit.setPassword("passEditwerwerada");

            repo.editEmployee(empployeeToEdit);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
