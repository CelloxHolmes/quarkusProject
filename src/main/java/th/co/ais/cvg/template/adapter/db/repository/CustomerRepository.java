package th.co.ais.cvg.template.adapter.db.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import th.co.ais.cvg.template.adapter.db.entity.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Customer create(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }

    public boolean delete(Customer customer) {
        entityManager.remove(customer);
        return true;
    }

    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class)
                .getResultList();
    }
}
