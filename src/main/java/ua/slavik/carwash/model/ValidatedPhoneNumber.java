package ua.slavik.carwash.model;

import lombok.Data;
import ua.slavik.carwash.exception.validators.PhoneNumberConstraint;

@Data
public class ValidatedPhoneNumber
{
    @PhoneNumberConstraint
    private String phoneNumber;

    @Override
    public String toString()
    {
        return phoneNumber;
    }
}
