package com.ncores.plaluvs.security;


import com.ncores.plaluvs.domain.user.User;
import com.ncores.plaluvs.domain.user.UserRoleEnum;
import com.ncores.plaluvs.exception.ErrorCode;
import com.ncores.plaluvs.exception.PlaluvsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRoleEnum userRole = user.getRole();
        String authority = userRole.getAuthority();

        SimpleGrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleAuthority);

        return authorities;
    }

    public static void UserCheck(UserDetailsImpl userDetails) throws PlaluvsException {
        if ( userDetails == null) {
            throw new PlaluvsException(ErrorCode.USER_NOT_LOGIN);
        }
    }

}
