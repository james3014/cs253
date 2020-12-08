package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import config.PostgresConnection;
import model.Customer;

public class InsertServiceImpl implements InsertService {

    private final PostgresConnection connection;

    public InsertServiceImpl(PostgresConnection connection) {
        this.connection = connection;
    }

    @Override
    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (customerId, firstName, lastName, contactNumber) VALUES ('"
                + customer.getCustomerId() + "', '" + customer.getFirstName() + "', '" + customer
                .getLastName() + "', '" + customer.getContactNumber() + "')";

        try {
            PreparedStatement
                preparedStatement =
                connection.connect().prepareStatement(sql);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Insert successful for primary key " + customer.getCustomerId());
                retrieveCustomer(customer.getCustomerId());
            } else {
                System.out.println("Insert has failed for primary key " + customer.getCustomerId());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void retrieveCustomer(String customerId) {
        String sql = "SELECT * FROM Customer where customerId = '" + customerId + "'";
        try {
            PreparedStatement preparedStatement = connection.connect().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = new Customer();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getString("customerId"));
                customer.setFirstName(resultSet.getString("firstName"));
                customer.setLastName(resultSet.getString("lastName"));
                customer.setContactNumber(resultSet.getString("contactNumber"));
            }
            System.out.println("Customer has been retrieved from database");
            System.out.print("Customer ID: " + customer.getCustomerId() + "\n");
            System.out.print("First Name: " + customer.getFirstName() + "\n");
            System.out.print("Last Name: " + customer.getLastName() + "\n");
            System.out.print("Contact Number: " + customer.getContactNumber() + "\n");
        } catch (SQLException e) {
            System.err.print("Select statement has failed for primary key " + customerId);
        }
    }

    @Override
    public Customer createCustomer() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.print("Enter customer id: ");
        customer.setCustomerId(scanner.next());
        System.out.print("Enter first name: ");
        customer.setFirstName(scanner.next());
        System.out.print("Enter last name: ");
        customer.setLastName(scanner.next());
        System.out.print("Enter contact number: ");
        customer.setContactNumber(scanner.next());
        return customer;
    }


}
