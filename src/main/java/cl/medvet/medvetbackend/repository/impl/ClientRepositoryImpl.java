package cl.medvet.medvetbackend.repository.impl;

import cl.medvet.medvetbackend.models.*;
import cl.medvet.medvetbackend.repository.IClientRepository;
import cl.medvet.medvetbackend.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements IClientRepository {

    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getConnection();
    }

    // this is only here to not have to create a whole service logIn for employees or clients.
    EmployeeRepositoryImpl emp = new EmployeeRepositoryImpl();

    // Method to get all the clients
    @Override
    public List<ClientModel> getAllCLients() {

        // create the list of employees
        List<ClientModel> clients = new ArrayList<>();

        //Query field
        try (Statement stmt = getConnection().createStatement();

             ResultSet rs = stmt.executeQuery("SELECT cl.rut_cliente, cl.nombre_cliente, " +
                     "cl.apellidos_cliente, cl.fono_cliente, cl.email_cliente, cl.contrasena_cliente, dr.id_direccion, dr.direccion\n" +
                     "FROM cliente cl JOIN direccion dr ON cl.DIRECCION_id_direccion = dr.id_direccion")) {

            while (rs.next()) {

                clients.add(mapClient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    // Method to get a client by rut
    @Override
    public ClientModel getClientByRut(String rut) {

        ClientModel client;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT cl.rut_cliente, cl.nombre_cliente, " +
                        "cl.apellidos_cliente, cl.fono_cliente, cl.email_cliente, cl.contrasena_cliente, dr.id_direccion, dr.direccion, dr.COMUNA_cut_comuna \n" +
                        "FROM cliente cl JOIN direccion dr ON cl.DIRECCION_id_direccion = dr.id_direccion WHERE rut_cliente = ?")) {

            stmt.setString(1, rut);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = mapClient(rs);
            } else {
                client = null;
            }

            return client;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ClientModel logIn(LogInModel usr) {

        ClientModel client = new ClientModel();

        EmployeeModel employee;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT cl.rut_cliente, cl.nombre_cliente, " +
                        "cl.apellidos_cliente, cl.fono_cliente, cl.email_cliente, cl.contrasena_cliente, dr.id_direccion, dr.direccion, COMUNA_cut_comuna \n" +
                        "FROM cliente cl JOIN direccion dr ON cl.DIRECCION_id_direccion = dr.id_direccion WHERE email_cliente = ?")) {

            stmt.setString(1, usr.getEmail());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = mapClient(rs);
            } else {
                client.setClientName("1");
            }

            if (client.getClientName().equals("1")){
                employee = emp.getEmployeeByEMail(usr.getEmail());

                if (employee != null){
                    client.setClientRut(employee.getRutEmployee());
                    client.setClientName(employee.getNameEmployee());
                    client.setClientPassword(employee.getPassword());
                    client.setClientEmail(employee.getEmailEmployee());
                    client.setClientEmailRecovery("1");
                    return client;
                }
            }

            return client;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

    // function that returns the communes
    public List<CommuneModel> getCommunes(){
        List<CommuneModel> communes = new ArrayList<>();

        String query = "SELECT * FROM comuna";

        try(PreparedStatement stmt = getConnection()
                .prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                communes.add(mapCommune(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return communes;
    }

    public CommuneModel mapCommune(ResultSet rs) throws SQLException {
        CommuneModel commune = new CommuneModel();

        commune.setCutComuna(rs.getString("cut_comuna"));
        commune.setNameCommune(rs.getString("nombre_comuna"));
        commune.setCutProv(rs.getString("PROVINCIA_cut_provincia"));

        return commune;
    }
    public ClientModel getClientByEmail(String email) {

        ClientModel client;

        try (PreparedStatement stmt = getConnection().
                prepareStatement("SELECT cl.rut_cliente, cl.nombre_cliente, " +
                        "cl.apellidos_cliente, cl.fono_cliente, cl.email_cliente, cl.contrasena_cliente, dr.id_direccion, dr.direccion\n" +
                        "FROM cliente cl JOIN direccion dr ON cl.DIRECCION_id_direccion = dr.id_direccion WHERE email_cliente = ?")) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = mapClient(rs);
            } else {
                client = null;
            }

            return client;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }

    // Method to delete a client by rut
    @Override
    public int deleteClient(String rut) {

        int res = 0;

        boolean qres = true;

        try ( PreparedStatement stmt = getConnection().
                prepareStatement("DELETE FROM cliente WHERE rut_cliente = ?")) {

            stmt.setString(1, rut);

            qres = stmt.execute();

            if (qres) {
                res = 1;
            } else {
                res = 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Method to edit a client
    @Override
    public int editClient(ClientModel client) {



        int res = 0;

        String clientQuery = "UPDATE cliente SET nombre_cliente = ?, " +
                "apellidos_cliente = ?, fono_cliente = ?, email_cliente = ?, email_recup = ?, contrasena_cliente = ?" +
                "WHERE rut_cliente = ?";

        try (PreparedStatement stmt = getConnection().
                prepareStatement(clientQuery)) {

            stmt.setString(1, client.getClientName());
            stmt.setString(2, client.getClientLastNames());
            stmt.setString(3, client.getClientPhone());
            stmt.setString(4, client.getClientEmail());
            stmt.setString(5, client.getClientEmailRecovery());
            stmt.setString(6, client.getClientPassword());
            stmt.setString(7, client.getClientRut());

            res = stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

        String addressQuery = "UPDATE direccion SET direccion = ?, COMUNA_cut_comuna = ? WHERE id_direccion = ?";

        try (PreparedStatement stmt = getConnection().
                prepareStatement(addressQuery)) {

            stmt.setString(1, client.getClientAddress().getNameAddress());
            stmt.setString(2, client.getClientAddress().getForeanIdComune());
            stmt.setInt(3, client.getClientAddress().getIdAddress());

            res = stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return res;
    }

    // Method to create a client
    @Override
    public int createClient(ClientModel client, PetModel pet) {

        int resPet = 0;
        String queryPet = "INSERT INTO mascota VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // first the owner
        int res = 0;
        String query = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getConnection().
                prepareStatement(query)) {

            stmt.setString(1, client.getClientRut());
            stmt.setString(2, client.getClientName());
            stmt.setString(3, client.getClientLastNames());
            stmt.setString(4, client.getClientPhone());
            stmt.setString(5, client.getClientEmail());
            stmt.setString(6, client.getClientEmailRecovery());
            stmt.setString(7, client.getClientPassword());
            stmt.setInt(8, client.getClientAddress().getIdAddress());

            res = stmt.executeUpdate();

            System.out.println("Registro Creado correctamente...");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // then the pet

        if ( res == 1 ) {

            try (PreparedStatement stmtPet = getConnection()
                    .prepareStatement(queryPet)) {

                stmtPet.setString(1,pet.getSpeciesPet());
                stmtPet.setString(2, pet.getBreedPet());
                stmtPet.setString(3,pet.getAgePet());
                stmtPet.setString(4, pet.getWeightPet());
                stmtPet.setString(5, pet.getSexPet());
                stmtPet.setString(6, pet.getObservationPet());
                stmtPet.setString(7, pet.getImage());
                stmtPet.setString(8, pet.getNamePet());
                stmtPet.setString(9, client.getClientRut());

                resPet = stmtPet.executeUpdate();

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }

        return resPet;
    }

    // Method to create an Address
    public int createAddress(AddressModel address) {

        int res = 0;
        String query = "INSERT INTO direccion VALUES (0,?,?)";
        try (PreparedStatement stmt = getConnection().
                prepareStatement(query)) {

            stmt.setString(1, address.getNameAddress());
            stmt.setString(2, address.getForeanIdComune());

            res = stmt.executeUpdate();

            System.out.println("Registro creado correctamente...");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    // Method to map a client
    private ClientModel mapClient(ResultSet rs) throws SQLException {

        ClientModel client = new ClientModel();

        client.setClientRut(rs.getString("rut_cliente"));
        client.setClientName(rs.getString("nombre_cliente"));
        client.setClientLastNames(rs.getString("apellidos_cliente"));
        client.setClientPhone(rs.getString("fono_cliente"));
        client.setClientEmail(rs.getString("email_cliente"));
        client.setClientPassword(rs.getString("contrasena_cliente"));

        AddressModel address = new AddressModel();

        address.setIdAddress(Integer.parseInt(rs.getString("id_direccion")));
        address.setNameAddress(rs.getString("direccion"));
        address.setForeanIdComune(rs.getString("COMUNA_cut_comuna"));

        client.setClientAddress(address);

        return client;
    }

    // Method to get an id of an address
    public int getIdAddress(AddressModel address){

        int res = 0;

        String query = "SELECT MAX(id_direccion) FROM direccion WHERE direccion = ? and COMUNA_cut_comuna = ?";
        try (PreparedStatement stmt = getConnection().
                prepareStatement(query)) {

            stmt.setString(1, address.getNameAddress());
            stmt.setString(2, String.valueOf(address.getForeanIdComune()));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                res = (rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}