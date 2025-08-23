package br.edu.ufape.plataforma.mentoria.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import br.edu.ufape.plataforma.mentoria.enums.UserRole;

public class UserDTOTest {

    @Test
    void testNoArgsConstructor() {
        UserDTO dto = new UserDTO();

        assertNull(dto.getEmail());
        assertNull(dto.getPassword());
        assertNull(dto.getRole());
    }

    @Test
    void testAllArgsConstructor() {
        String email = "user@example.com";
        String password = "Password123!";
        UserRole role = UserRole.MENTORADO;

        UserDTO dto = new UserDTO(email, password, role);

        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
        assertEquals(role, dto.getRole());
    }

    @Test
    void testSetAndGetEmail() {
        UserDTO dto = new UserDTO();
        String email = "test@domain.com";

        dto.setEmail(email);

        assertEquals(email, dto.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        UserDTO dto = new UserDTO();
        String password = "SecurePass123!";

        dto.setPassword(password);

        assertEquals(password, dto.getPassword());
    }

    @Test
    void testSetAndGetRole() {
        UserDTO dto = new UserDTO();
        UserRole role = UserRole.MENTOR;

        dto.setRole(role);

        assertEquals(role, dto.getRole());
    }

    @Test
    void testSetNullValues() {
        UserDTO dto = new UserDTO();

        dto.setEmail(null);
        dto.setPassword(null);
        dto.setRole(null);

        assertNull(dto.getEmail());
        assertNull(dto.getPassword());
        assertNull(dto.getRole());
    }

    @Test
    void testSetEmptyStrings() {
        UserDTO dto = new UserDTO();

        dto.setEmail("");
        dto.setPassword("");

        assertEquals("", dto.getEmail());
        assertEquals("", dto.getPassword());
    }

    @Test
    void testAllUserRoles() {
        UserDTO dto = new UserDTO();

        // Test all possible user roles
        UserRole[] roles = UserRole.values();

        for (UserRole role : roles) {
            dto.setRole(role);
            assertEquals(role, dto.getRole());
        }
    }

    @Test
    void testValidEmailFormats() {
        UserDTO dto = new UserDTO();

        String[] validEmails = {
                "user@example.com",
                "test.email@domain.org",
                "user123@company.co.uk",
                "name+tag@site.com",
                "user_name@example-site.com"
        };

        for (String email : validEmails) {
            dto.setEmail(email);
            assertEquals(email, dto.getEmail());
        }
    }

    @Test
    void testValidPasswordFormats() {
        UserDTO dto = new UserDTO();

        String[] validPasswords = {
                "Password123!",
                "Secure@Pass1",
                "MyP@ssw0rd",
                "Strong123$",
                "Valid&Pass9"
        };

        for (String password : validPasswords) {
            dto.setPassword(password);
            assertEquals(password, dto.getPassword());
        }
    }

    @Test
    void testCompleteUserCreation() {
        UserDTO dto = new UserDTO();

        // Set all fields
        dto.setEmail("complete@user.com");
        dto.setPassword("CompletePass123!");
        dto.setRole(UserRole.MENTOR);

        // Verify all fields
        assertEquals("complete@user.com", dto.getEmail());
        assertEquals("CompletePass123!", dto.getPassword());
        assertEquals(UserRole.MENTOR, dto.getRole());
    }

    @Test
    void testConstructorWithMentorRole() {
        String email = "mentor@company.com";
        String password = "MentorPass123!";
        UserRole role = UserRole.MENTOR;

        UserDTO dto = new UserDTO(email, password, role);

        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
        assertEquals(UserRole.MENTOR, dto.getRole());
    }

    @Test
    void testConstructorWithMentoredRole() {
        String email = "student@university.edu";
        String password = "StudentPass123!";
        UserRole role = UserRole.MENTORADO;

        UserDTO dto = new UserDTO(email, password, role);

        assertEquals(email, dto.getEmail());
        assertEquals(password, dto.getPassword());
        assertEquals(UserRole.MENTORADO, dto.getRole());
    }

    @Test
    void testPasswordWithSpecialCharacters() {
        UserDTO dto = new UserDTO();

        String[] passwordsWithSpecialChars = {
                "Test@123",
                "Pass#456",
                "Secure$789",
                "Strong!012",
                "Valid&345",
                "Good%678",
                "Nice*901"
        };

        for (String password : passwordsWithSpecialChars) {
            dto.setPassword(password);
            assertEquals(password, dto.getPassword());
        }
    }

    @Test
    void testEmailCaseSensitivity() {
        UserDTO dto = new UserDTO();

        String[] emails = {
                "USER@EXAMPLE.COM",
                "user@example.com",
                "User@Example.Com",
                "uSeR@eXaMpLe.CoM"
        };

        for (String email : emails) {
            dto.setEmail(email);
            assertEquals(email, dto.getEmail());
        }
    }

    @Test
    void testLongEmailAddress() {
        UserDTO dto = new UserDTO();
        String longEmail = "very.long.email.address.for.testing.purposes@very-long-domain-name-for-testing.com";

        dto.setEmail(longEmail);

        assertEquals(longEmail, dto.getEmail());
        assertTrue(dto.getEmail().length() > 50);
    }

    @Test
    void testComplexPassword() {
        UserDTO dto = new UserDTO();
        String complexPassword = "C0mpl3x@P@ssw0rd!2024";

        dto.setPassword(complexPassword);

        assertEquals(complexPassword, dto.getPassword());
        assertTrue(dto.getPassword().length() > 15);
    }

    @Test
    void testFieldIndependence() {
        UserDTO dto = new UserDTO();

        dto.setEmail("test@example.com");
        assertEquals("test@example.com", dto.getEmail());
        assertNull(dto.getPassword());
        assertNull(dto.getRole());

        dto.setPassword("TestPass123!");
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("TestPass123!", dto.getPassword());
        assertNull(dto.getRole());

        dto.setRole(UserRole.MENTOR);
        assertEquals("test@example.com", dto.getEmail());
        assertEquals("TestPass123!", dto.getPassword());
        assertEquals(UserRole.MENTOR, dto.getRole());
    }

    @Test
    void testRoleModification() {
        UserDTO dto = new UserDTO();

        dto.setRole(UserRole.MENTORADO);
        assertEquals(UserRole.MENTORADO, dto.getRole());

        dto.setRole(UserRole.MENTOR);
        assertEquals(UserRole.MENTOR, dto.getRole());

        dto.setRole(null);
        assertNull(dto.getRole());
    }

    @Test
    void testMinimumValidPassword() {
        UserDTO dto = new UserDTO();
        String minPassword = "Pass123!";

        dto.setPassword(minPassword);

        assertEquals(minPassword, dto.getPassword());
        assertEquals(8, dto.getPassword().length());
    }
}