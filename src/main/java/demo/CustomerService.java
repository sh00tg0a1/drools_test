package demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cx on 2018/11/28.
 */
public class CustomerService {
    public List<Customer> getCustomerList() {
        List<Customer> result = new ArrayList<Customer>();
        result.add(new Customer("A", "A", "CN"));
        result.add(new Customer("B", "B", "GB"));
        result.add(new Customer("C", "C", "US"));

        return result;
    }
}
