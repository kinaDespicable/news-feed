package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.repository.UserRepository;
import dev.dani.strong.newsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
