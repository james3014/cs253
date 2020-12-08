import config.PostgresConnection;
import model.Customer;
import service.InsertService;
import service.InsertServiceImpl;

public class Main {

    public static void main(String[] args) {
        // Testing customer insert and retrieve
        Customer customerTest1 = Customer.builder()
            .customerId("07")
            .firstName("John")
            .lastName("Smith")
            .contactNumber("07728462748")
            .build();

        PostgresConnection postgresConnection = new PostgresConnection();
        InsertService insertService = new InsertServiceImpl(postgresConnection);
        Customer customerTest2 = insertService.createCustomer();
        insertService.insertCustomer(customerTest1);
        insertService.insertCustomer(customerTest2);

    }
}
