package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetail implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String surname;

    @Email
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Builder.Default
    private UserPermissionEnum role = UserPermissionEnum.USER;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @Column(name = "isHost")
    private boolean isHost;

    @Column(length = 1, columnDefinition = "CHAR(1) DEFAULT 'M'")
//    @Pattern(regexp = "[M|F]", message = "The field 'gender' must be filled.")
    public String gender;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_detail")
    private Set<ReviewScore> reviewScores = new HashSet<>();

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      UserPermissionEnum role,
                      boolean isEnabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
    }


    public UserDetail(String name,
                      String surname,
                      String email,
                      boolean isEnabled,
                      String password
                      ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isEnabled = isEnabled;
        this.password = password;

    }

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      String gender,
                      UserPermissionEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.role = role;
    }

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }


    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      UserPermissionEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      boolean isHost,
                      String gender) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isHost = isHost;
        this.role = (isHost) ? UserPermissionEnum.ADMIN : UserPermissionEnum.USER;
        this.gender = gender;
    }

    public UserDetail(String name,
                      String surname,
                      String email,
                      String password,
                      boolean isHost,
                      boolean isEnabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.isHost = isHost;
        this.role = (isHost) ? UserPermissionEnum.ADMIN : UserPermissionEnum.USER;
        this.isEnabled = isEnabled;
    }


    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isEnabled=" + isEnabled +
                ", reviewScores=" + reviewScores +
                '}';
    }

//    public void setEnabled(boolean enabled) {
//        is_enabled = enabled;
//    }

    public Set<ReviewScore> getReviewScores() {
        return reviewScores;
    }

    public void setReviewScores(Set<ReviewScore> reviewScores) {
        this.reviewScores = reviewScores;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPermissionEnum getRole() {
        return role;
    }

    public void setRole(UserPermissionEnum role) {
        this.role = role;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }
}
