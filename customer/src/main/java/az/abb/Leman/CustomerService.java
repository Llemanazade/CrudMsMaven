package az.abb.Leman;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CustomerService(CustomerRepo customerRepo, OrderRepo orderRepo) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .status(request.status())
                .build();

        customerRepo.save(customer);

    }
    public List<Customer> getCustomers () {

        return customerRepo.findAll();

    }
    public List<Order> getOrders () {

        return orderRepo.findAll();

    }


    public void deleteCustomer(Integer id) {
        customerRepo.deleteById(id);
    }

    public ResponseEntity<Customer> updateCustomer(Integer id, CustomerRegistrationRequest request) {
        Optional<Customer> customerData = customerRepo.findById(id);
        if(customerData.isPresent()){
            Customer customer = customerData.get();
            if(!request.email().isEmpty())
                customer.setEmail(request.email());
            if(!request.firstName().isEmpty())
                customer.setFirstName(request.firstName());
            if(!request.lastName().isEmpty())
                customer.setLastName(request.lastName());
            if(!request.status().isEmpty())
                customer.setStatus(request.status());
            return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
