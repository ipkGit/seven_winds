package com.example.seven_winds.model;


import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    @Min(1) @Max(999999999)
    private Long id;

    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 16)
    private String name;

    @Column(name = "sur_name")
    @NotBlank
    @Size(min = 1, max = 16)
    private String surName;

    @Column(name = "patronymic")
    @NotBlank
    @Size(min = 1, max = 16)
    private String patronymic;

    @Column(name = "phone_number")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phoneNumber;

    @Column(name = "email")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Size(max = 30)
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
