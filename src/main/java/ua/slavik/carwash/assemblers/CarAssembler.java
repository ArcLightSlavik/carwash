package ua.slavik.carwash.assemblers;

import org.springframework.stereotype.Component;
import ua.slavik.carwash.VO.CarVO;
import ua.slavik.carwash.VO.CreateCarVO;
import ua.slavik.carwash.VO.UpdateCarVO;
import ua.slavik.carwash.model.Car;
import ua.slavik.carwash.model.Customer;
import ua.slavik.carwash.service.CustomerService;

import java.util.Optional;

@Component
public class CarAssembler
{
    public Car toCar(CreateCarVO createCarVO)
    {
        Car car = new Car();
        Optional<Customer> customer = CustomerService.getCustomerById(CreateCarVO.getCustomerId());
        if (customer.isPresent())
        {
            car.setCustomer(customer.get());
        }
        car.setModel(createCarVO.getModel());
        car.setRegistrationNumber(createCarVO.getRegistrationNumber());
        car.setJobs(createCarVO.getJobs());

        return car;
    }

    public CarVO toCarVO(Car car)
    {
        CarVO carVO = new CarVO();
        carVO.setId(car.getId());
        carVO.setCustomer(car.getCustomer());
        carVO.setModel(car.getModel());
        carVO.setRegistrationNumber(car.getRegistrationNumber());
        carVO.setJobs(car.getJobs());

        return carVO;
    }

    public Car toCar(UpdateCarVO updateCarVO)
    {
        Car car = new Car();
        car.setId(updateCarVO.getId());
        car.setCustomer(updateCarVO.getCustomer());
        car.setModel(updateCarVO.getModel());
        car.setRegistrationNumber(updateCarVO.getRegistrationNumber());
        car.setJobs(updateCarVO.getJobs());

        return car;
    }
}
