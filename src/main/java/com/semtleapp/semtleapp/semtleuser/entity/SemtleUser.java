package com.semtleapp.semtleapp.semtleuser.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "semtle_user")
public class SemtleUser extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(
            mappedBy = "semtleUser",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<SemtleBook> semtleBookList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : role.split(","))
            authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public SemtleUser(String email, String password, String role, List<SemtleBook> semtleBookList) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.semtleBookList = semtleBookList;
    }

    public SemtleUserDto toDto() {
        return SemtleUserDto.builder()
                .email(email)
                .password(password)
                //.role(role)
                .build();
    }

}
