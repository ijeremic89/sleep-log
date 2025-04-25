package com.noom.interview.fullstack.sleep.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

  @Column(name = "created_date", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @Column(name = "modified_date")
  private LocalDateTime modifiedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public @NotBlank(message = "First name cannot be blank") String getFirstName() {
    return firstName;
  }

  public void setFirstName(@NotBlank(message = "First name cannot be blank") String firstName) {
    this.firstName = firstName;
  }

  public @NotBlank(message = "Last name cannot be blank") String getLastName() {
    return lastName;
  }

  public void setLastName(@NotBlank(message = "Last name cannot be blank") String lastName) {
    this.lastName = lastName;
  }

  public @NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format")
  String getEmail() {
    return email;
  }

  public void setEmail(
      @NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format")
          String email) {
    this.email = email;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(LocalDateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }
}
