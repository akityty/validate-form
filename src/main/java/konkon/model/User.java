package konkon.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Component
@Entity
@Table(name = "users")
public class User implements Validator {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotEmpty
  @Size(min = 5, max = 45)
  private String firstName;
  @NotEmpty
  @Size(min = 5, max = 45)
  private String lastName;
  private String phoneNumber;

  public User() {
  }

  public User(@NotEmpty @Size(min = 5, max = 45) String firstName, @NotEmpty @Size(min = 5, max = 45) String lastName, String phoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    String phoneNumber = user.getPhoneNumber();
    ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phoneNumber.empty");
    if (phoneNumber.length() > 11 || phoneNumber.length() < 10) {
      errors.rejectValue("phoneNumber", "phoneNumber.length");
    }
    if (!phoneNumber.startsWith("0")) {
      errors.rejectValue("phoneNumber", "phoneNumber.startsWith");
    }
    if (!phoneNumber.matches("(^$|[0-9]*$)")) {
      errors.rejectValue("phoneNumber", "phoneNumber.matches");
    }
  }
}
