package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.PostgresConnection;
import model.Customer;

public class InsertServiceImpl implements InsertService {

    private final PostgresConnection connection;

    public InsertServiceImpl(PostgresConnection connection) {
        this.connection = connection;
    }

    public void insertCustomer(Customer customer) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("INSERT INTO customer (customerId, firstName, lastName, contactNumber) VALUES (")
//            .append(customer.getCustomerId()).append(", ").append(customer.getFirstName())
//            .append(", ").append(customer.getLastName()).append(", ")
//            .append(customer.getContactNumber()).append(")");

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

    private void retrieveCustomer(String customerId) {
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


}
