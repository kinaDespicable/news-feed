package dev.dani.strong.newsfeed.service.implementaion;

import dev.dani.strong.newsfeed.repository.RoleRepository;
import dev.dani.strong.newsfeed.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

}
