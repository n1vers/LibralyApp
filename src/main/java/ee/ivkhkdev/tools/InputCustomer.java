package ee.ivkhkdev.tools;

import ee.ivkhkdev.interfaces.Input;
import ee.ivkhkdev.model.Customer;

import java.util.Scanner;

public class InputCustomer {
    public Customer newCustomer(Input input){
        Customer customer = new Customer();
        System.out.println("Имя пользователя: ");
        customer.setFirstName(input.nextLine());
        System.out.println("Фамилия пользователя: ");
        customer.setLastName(input.nextLine());
        System.out.println("Телефон: ");
        customer.setPhone(input.nextLine());
        return customer;
    }
}
