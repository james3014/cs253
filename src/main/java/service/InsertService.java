package service;

import model.Customer;

public interface InsertService {

    void insertCustomer(Customer customer);

    void retrieveCustomer(String customerId);

    Customer createCustomer();
}
