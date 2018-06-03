package ua.slavik.carwash.assemblers;

import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateCustomerVO;
import ua.slavik.carwash.VO.CustomerVO;
import ua.slavik.carwash.VO.UpdateCustomerVO;
import ua.slavik.carwash.model.Customer;

@Component
public class CustomerAssembler
{
    public Customer toCustomer(CreateCustomerVO createCustomerVO)
    {
        Customer customer = new Customer();
        customer.setFirstName(createCustomerVO.getFirstName());
        customer.setLastName(createCustomerVO.getLastName());
        customer.setPhoneNumber(createCustomerVO.getPhoneNumber());

        return customer;
    }
    public CustomerVO toCustomerVO(Customer customer)
    {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setId(customer.getId());
        customerVO.setFirstName(customer.getFirstName());
        customerVO.setLastName(customer.getLastName());
        customerVO.setPhoneNumber(customer.getPhoneNumber());
        customerVO.setCar(customer.getCar());

        return customerVO;
    }

    public Customer toCustomer(UpdateCustomerVO updateCustomerVO)
    {
        Customer customer = new Customer();
        customer.setId(updateCustomerVO.getCustomerId());
        customer.setFirstName(updateCustomerVO.getFirstName());
        customer.setLastName(updateCustomerVO.getLastName());
        customer.setPhoneNumber(updateCustomerVO.getPhoneNumber());
        return customer;
    }

}
