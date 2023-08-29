package com.semtleapp.semtleapp.semtleuser.entity;

import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "semtle_user")
public class SemtleUser extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(name = "username", unique = true)
    private String username; //로그인 시 사용할 아이디
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @Column(name = "social")
    private String social;

//    @OneToMany(
//            mappedBy = "semtleUser",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    @JsonManagedReference
//    private List<SemtlePost> semtlePostList = new ArrayList<>();
//
//    @OneToMany(
//            mappedBy = "semtleUser",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    @JsonManagedReference
//    private List<SemtleBook> semtleBookList = new ArrayList<>();

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

//    @Builder
//    public SemtleUser(String email, String password, String role, String social,
//                      List<SemtlePost> semtlePostList, List<SemtleBook> semtleBookList) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//        this.semtlePostList = semtlePostList;
//        this.semtleBookList = semtleBookList;
//    }

    public SemtleUserDto toDto() {
        return SemtleUserDto.builder()
                .email(email)
                .password(password)
                //.role(role)
                .build();
    }

//    public void addPost(SemtlePost semtlePost) {
//        this.semtlePostList.add(semtlePost);
//        semtlePost.setSemtleUser(this);
//    }
}
