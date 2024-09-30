package ee.ivkhkdev.service;

import ee.ivkhkdev.App;
import ee.ivkhkdev.model.Customer;
import ee.ivkhkdev.tools.InputCustomer;

public class CustomerService {

    public void createCustomer() {
        InputCustomer inputCustomer = new InputCustomer();
        Customer customer=inputCustomer.newCustomer();
        App.customers[0]=customer;
    }

}
