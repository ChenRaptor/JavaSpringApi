package fr.iut.td01.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import fr.iut.td01.models.UserData;
import fr.iut.td01.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
        authorities.add(AuthorityUtils.createAuthorityList("ROLE_USER").get(0));
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        UserData user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(user.getLogin(), "{noop}"+user.getPassword(), authorities);
    }
}