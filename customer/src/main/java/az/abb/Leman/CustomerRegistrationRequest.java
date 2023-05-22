package az.abb.Leman;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String status) {
}
