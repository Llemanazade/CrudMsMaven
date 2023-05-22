package az.abb.Leman;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("New customer was registration{}!", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer (@PathVariable Integer id) {
        log.info("Customer was deleted!");
        customerService.deleteCustomer(id);
    }

    @PostMapping("/list")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();

    }



    @PutMapping("/{id}")
    public void updateCustomer (@PathVariable Integer id, @RequestBody CustomerRegistrationRequest request) {
        log.info("Customer was updated{}!", request);
        customerService.updateCustomer(id, request);
    }


}
