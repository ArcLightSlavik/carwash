package ua.slavik.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slavik.carwash.model.Service;
import ua.slavik.carwash.repository.ServiceRepository;
import java.util.Optional;

@RestController
public class ServiceController
{
    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping(value = "/services")
    public ResponseEntity createService(@RequestBody Service service)
    {
        serviceRepository.save(service);

        return new ResponseEntity(service , HttpStatus.OK);
    }

    @GetMapping("/services")
    public Iterable<Service> getService()
    {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public ResponseEntity getService(@PathVariable("id") Long id)
    {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent())
        {
            return new ResponseEntity(service, HttpStatus.OK);
        }
        return new ResponseEntity("no service id found" + id , HttpStatus.NOT_FOUND);
    }

    @PutMapping ("/services/{id}")
    public ResponseEntity updateService(@PathVariable Long id, @RequestBody Service service)
    {
        service = serviceRepository.save(service);
        return new ResponseEntity(service, HttpStatus.OK);
    }

    @DeleteMapping ("/services/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id)
    {
        boolean customerExists = serviceRepository.existsById(id);

        if(customerExists)
        {
            serviceRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("no service found by id" + id , HttpStatus.NOT_FOUND);
    }
}
