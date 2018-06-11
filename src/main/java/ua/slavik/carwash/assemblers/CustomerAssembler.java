package ua.slavik.carwash.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CreateCustomerVO;
import ua.slavik.carwash.VO.CustomerVO;
import ua.slavik.carwash.VO.UpdateCustomerVO;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CarService;

@Component
public class CustomerAssembler
{
    @Autowired
    private CarService carService;

    public Customer toCustomer(CreateCustomerVO createCustomerVO)
    {
        Customer customer = new Customer();
        customer.setFirstName(createCustomerVO.getFirstName());
        customer.setLastName(createCustomerVO.getLastName());
        customer.setPhoneNumber(createCustomerVO.getPhoneNumber());
        customer.setCar(carService.getCarById(createCustomerVO.getCarId()).orElse(null));

        return customer;
    }
    public CustomerVO toCustomerVO(Customer customer)
    {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setId(customer.getId());
        customerVO.setFirstName(customer.getFirstName());
        customerVO.setLastName(customer.getLastName());
        customerVO.setPhoneNumber(customer.getPhoneNumber());
        customerVO.setCarId(customer.getCar().getId());

        return customerVO;
    }

    public Customer toCustomer(UpdateCustomerVO updateCustomerVO)
    {
        Customer customer = new Customer();
        customer.setId(updateCustomerVO.getId());
        customer.setFirstName(updateCustomerVO.getFirstName());
        customer.setLastName(updateCustomerVO.getLastName());
        customer.setPhoneNumber(updateCustomerVO.getPhoneNumber());
        customer.setCar(carService.getCarById(updateCustomerVO.getCarId()).orElse(null));

        return customer;
    }

}
