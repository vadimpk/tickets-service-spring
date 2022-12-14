package com.naukma.ticketsservice.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository repository;

    public UserPrincipalDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserPrincipal(user.get());
    }

    public void update(Long id, String email, String password, String firstName, String lastName) {
        repository.updateById(id, email, password, firstName, lastName);
    }
}
