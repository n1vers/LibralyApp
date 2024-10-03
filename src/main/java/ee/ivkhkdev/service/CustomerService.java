package ee.ivkhkdev.service;

import ee.ivkhkdev.App;
import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;
import ee.ivkhkdev.tools.ConsoleInput;
import ee.ivkhkdev.tools.InputCustomer;

import java.util.Scanner;

public class CustomerService {

    public void createCustomer(Input input) {
        InputCustomer inputCustomer = new InputCustomer();
        Customer customer = inputCustomer.newCustomer(input);
        App.customers[0] = customer;
        System.out.println("Ползователь успешно создан");
    }

}
