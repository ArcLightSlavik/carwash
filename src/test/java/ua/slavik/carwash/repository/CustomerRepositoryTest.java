package ua.slavik.carwash.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.slavik.carwash.model.Customer;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void repositorySavesCustomer()
    {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Wick");

        Customer result = customerRepository.save(customer);

        assertEquals(result.getFirstName(), "John");
        assertEquals(result.getLastName(), "Wick");
    }
    //without EntityManager
}
