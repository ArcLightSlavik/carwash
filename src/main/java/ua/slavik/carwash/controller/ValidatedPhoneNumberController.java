package ua.slavik.carwash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.slavik.carwash.model.ValidatedPhoneNumber;
import javax.validation.Valid;

@Controller
public class ValidatedPhoneNumberController
{

    @GetMapping("/validatePhoneNumber")
    public String loadFormPage(Model m)
    {
        m.addAttribute("validatedPhone", new ValidatedPhoneNumber());
        return "phoneHome";
    }

    @PostMapping("/addValidatePhoneNumber")
    public String submitForm(@Valid ValidatedPhoneNumber validatedPhone, BindingResult result, Model m)
    {
        if (result.hasErrors()) {
            return "phoneHome";
        }

        m.addAttribute("message", "Successfully saved phone: " + validatedPhone.toString());
        return "phoneHome";
    }
}
