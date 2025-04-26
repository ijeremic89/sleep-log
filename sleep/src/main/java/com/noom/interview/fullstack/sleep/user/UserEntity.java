package com.noom.interview.fullstack.sleep.user;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  @NotBlank(message = "First name cannot be blank")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotBlank(message = "Last name cannot be blank")
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Invalid email format")
  private String email;
}
