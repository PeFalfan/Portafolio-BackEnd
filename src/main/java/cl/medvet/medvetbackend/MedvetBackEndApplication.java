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
    }
}
