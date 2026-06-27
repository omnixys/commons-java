package com.omnixys.commons.model;

import com.omnixys.commons.enums.PhoneNumberType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void shouldCreateValidPhoneNumber() {
        var phone = new PhoneNumber(PhoneNumberType.MOBILE, "+49", "123456789", null, true);
        assertEquals(PhoneNumberType.MOBILE, phone.type());
        assertEquals("+49", phone.countryCode());
        assertTrue(phone.isPrimary());
    }

    @Test
    void shouldRejectNullCountryCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new PhoneNumber(PhoneNumberType.MOBILE, null, "123", null, false));
    }

    @Test
    void shouldRejectNullNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new PhoneNumber(PhoneNumberType.MOBILE, "+49", null, null, false));
    }
}
