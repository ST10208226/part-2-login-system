import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {
    Login user;

    @Before
    public void setUp() {
        user = new Login("kyl_1", "Ch&&sec@ke99!", "First", "Last");
    }

    @Test
    public void testCheckUserName() {
        assertTrue(user.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testRegisterUser() {
        assertEquals("Registration successful", user.registerUser());
    }

    @Test
    public void testLoginUser() {
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }
}
