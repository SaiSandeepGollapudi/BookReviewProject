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
    private Long id;
    private String username;
    private String password;
    private String authority;
    private String email;
    private String phonenumber;

//    public void setEmail(String email) {// here the email is tightly coupled with the object we are referiing to
//        //This method directly sets the email attribute of the current instance of the User class. used to directly modify the state of an object.
//        this.email = email;//
//    }
//    public User setEmail(String email, User user) {// These methods ae directly designed like this by @Builder.It takes an email parameter along with a User object,
//        // sets the email attribute of the provided User object, and returns the same object.here setEmail is an independent method. It doesn't directly modify the state
//        //  of the current object but rather returns a new instance with the email set.
//       user.setEmail(email);
//    return user;
//}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {// to return authorities of a user, returns a collection of objects that are subtypes of GrantedAuthority
//Collection-return type of the method,<? extends GrantedAuthority> - ? symbol represents an unknown type. extends GrantedAuthority denotes that the unknown type must be a subtype of
// GrantedAuthority.It represents the authority granted to the user, such as "USER" or "ADMIN". Using a wildcard allows flexibility in the types of authorities that can be returned.
if(StringUtils.hasText(authority)){//StringUtils class provided by the Spring Framework's,used to validate input strings,to check if the authority attribute of a user is not empty
            //authority.split(",");// what if I want to give two authorities like ADMIN/USER or ADMIN/manager so we use this;
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
return Collections.emptyList();// is a defensive measure to handle the case where the authority string is empty or null,It shows that the user has no granted authorities in such cases.
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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
