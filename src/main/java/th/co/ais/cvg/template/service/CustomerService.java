package th.co.ais.cvg.template.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import th.co.ais.cvg.template.adapter.db.entity.Customer;
import th.co.ais.cvg.template.adapter.db.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.create(customer);
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(Long.valueOf(id));
    }

    public List<Customer> getAllCustomers() {
        // Retrieve all customers from the database
        return customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomer(String id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(Long.valueOf(id));
        if (existingCustomer != null) {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            // ... update other properties as needed

            return customerRepository.update(existingCustomer);
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteCustomer(String id) {
        Customer existingCustomer = customerRepository.findById(Long.valueOf(id));
        if (existingCustomer != null) {
            return customerRepository.delete(existingCustomer);
        } else {
            return false;
        }
    }
}
