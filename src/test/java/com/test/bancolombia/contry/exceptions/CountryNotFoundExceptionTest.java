package com.test.bancolombia.contry.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryNotFoundExceptionTest {

    @Test
    public void testConstructor() {
        String errorMessage = "Country not found";
        CountryNotFoundException exception = new CountryNotFoundException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }
}