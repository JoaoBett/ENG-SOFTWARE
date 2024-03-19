package pt.ipleiria.estg.dei.esoft.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipleiria.estg.ei.dei.esoft.Contact;
import static org.junit.jupiter.api.Assertions.*;
public class ContactTestCase {
    @Test
    public void testCreateContact() {
        var contact = new Contact("foo", "bar", "912 345 678");
        assertEquals("foo", contact.getFirstName());
        assertEquals("bar", contact.getLastName());
        assertEquals("912 345 678", contact.getPhone());

    }
/*
    @Test
    public void testFooBar() {
        fail("I fail with this message"); // Try this!
    }
*/


}
