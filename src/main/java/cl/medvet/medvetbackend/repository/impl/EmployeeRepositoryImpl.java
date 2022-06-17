package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.EmployeeModel;
import cl.medvet.medvetbackend.repository.IEmployeeRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements IEmployeeRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    // Method to get all the employees in the database.
    @Override
    public List<EmployeeModel> getAllEmployees() {

        // create the list of the employees
        List<EmployeeModel> employees = new ArrayList<>();

        // Make the query to the database
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM empleado")) {
            while (rs.next()) {

                // for every record or employee, we create one,
                // set the values of the resultSet and we add them to the list,
                // that we are going to send back as a response.
                employees.add(mapEmployee(rs));

            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return employees;
    }

    // Method to get an employee, by his rut ( no DV )!
    @Override
    public EmployeeModel getEmployeeByRut(String rutEmployee) {

        EmployeeModel employee;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM empleado WHERE rut_empleado = ?")) {

            stmt.setString(1, rutEmployee);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                employee = mapEmployee(rs);
            }else {
                employee = null;
            }

            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmployeeModel getEmployeeByEMail(String email) {
        EmployeeModel employee;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT * FROM empleado WHERE email_empleado = ?")) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                employee = mapEmployee(rs);
            }else {
                employee = null;
            }

            return employee;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to delete an employee, by rut.
    @Override
    public int deleteEmployee(String rut) {

        int res = 0;

        try ( PreparedStatement stmt = getConnection().
                prepareStatement("DELETE FROM empleado WHERE rut_empleado = ?")) {
            stmt.setString(1, rut);


            res =  stmt.executeUpdate();;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Method for edit the Employee
    @Override
    public int editEmployee(EmployeeModel employee) {

        int res = 0;

        String query = "UPDATE empleado SET nombre_empleado = ?, " +
                "email_empleado = ?, especialidad = ?, TIPO_EMPLEADO_id_tipo_empleado = ?, contrasena_empleado = ? , estado_empleado = ? " +
                "WHERE rut_empleado = ?";

        try (PreparedStatement stmt = getConnection().
                prepareStatement(query)) {

            stmt.setString(1, employee.getNameEmployee());
            stmt.setString(2, employee.getEmailEmployee());
            stmt.setString(3, employee.getSpeciality());
            stmt.setInt(4, employee.getTypeEmployee());
            stmt.setString(5, employee.getPassword());
            stmt.setString(6, employee.getStatusEmployee());
            stmt.setString(7, employee.getRutEmployee());


            res = stmt.executeUpdate();

        } catch (SQLException e){
            System.out.println("Error al actualizar empleado...");
            e.printStackTrace();
            res = -1;
        }

        return res;
    }

    // Method to create an Employee
    @Override
    public int createEmployee(EmployeeModel employee) {

        int res = 0;
        String query = "INSERT INTO empleado VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().
                prepareStatement(query)) {

            stmt.setString(1, employee.getRutEmployee());
            stmt.setString(2, employee.getNameEmployee());
            stmt.setString(3, employee.getEmailEmployee());
            stmt.setString(4, employee.getSpeciality());
            stmt.setInt(5, employee.getTypeEmployee());
            stmt.setString(6, employee.getPassword());
            stmt.setString(7, employee.getStatusEmployee());

            res = stmt.executeUpdate();

            System.out.println("Registro Creado correctamente...");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Method to build up an Employee
    private EmployeeModel mapEmployee(ResultSet rs) throws SQLException {
        EmployeeModel employee = new EmployeeModel();

        employee.setRutEmployee(rs.getString("rut_empleado"));
        employee.setNameEmployee(rs.getString("nombre_empleado"));
        employee.setEmailEmployee(rs.getString("email_empleado"));
        employee.setSpeciality(rs.getString("especialidad"));
        employee.setTypeEmployee(Integer.parseInt(rs.getString("TIPO_EMPLEADO_id_tipo_empleado")));
        employee.setPassword(rs.getString("contrasena_empleado"));
        employee.setStatusEmployee(rs.getString("estado_empleado"));

        return employee;
    }
}