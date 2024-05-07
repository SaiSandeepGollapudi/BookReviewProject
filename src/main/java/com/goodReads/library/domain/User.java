package com.goodReads.library.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Entity
@Builder
public class User implements UserDetails {
//.Entity class is responsible for Authentication and also Authorisation as it specifies Authorities
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String authority;
        private String email;
    private String phonenumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {// to give authorities to a user
        if(StringUtils.hasText(authority)){//if my role is not empty
            //authority.split(",");// what if I want to give two authorities like ADMIN/USER or ADMIN/manager we can use this;
            return Arrays.stream(authority.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//authority.split(","): This splits the authority string into an array of strings using commas as the delimiter. For example, if authority is "ADMIN,USER",
// this will result in an array with two elements: "ADMIN" and "USER".
//Arrays.stream(...): This method creates a stream from the array of strings obtained from the split operation. It allows you to perform various stream operations on the
// elements of the array.
//.map(SimpleGrantedAuthority::new): This maps each string in the stream to a SimpleGrantedAuthority object. It effectively converts each authority string into a
// SimpleGrantedAuthority instance. This is done using a constructor reference SimpleGrantedAuthority::new, which creates a SimpleGrantedAuthority object from a string.
//.collect(Collectors.toList()): This collects the elements of the stream into a List. After mapping each authority string to a SimpleGrantedAuthority object,
// this operation collects these objects into a list.
}
        return Collections.emptyList();
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
       // We can write our logic that if(lastUpdateTime > 6months) then return false
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
