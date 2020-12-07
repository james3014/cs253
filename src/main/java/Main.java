import config.PostgresConnection;
import model.Customer;
import service.InsertService;
import service.InsertServiceImpl;

public class Main {

    public static void main(String[] args) {

        // Testing customer insert and retrieve
        Customer customer = Customer.builder()
            .customerId("07")
            .firstName("John")
            .lastName("Smith")
            .contactNumber("07728462748")
            .build();

        PostgresConnection postgresConnection = new PostgresConnection();
        InsertService insertService = new InsertServiceImpl(postgresConnection);
        insertService.insertCustomer(customer);

    }
}
