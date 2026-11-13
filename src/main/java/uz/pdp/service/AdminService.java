package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public List<Map<String, Object>> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> {

                    Map<String, Object> map = new HashMap<>();

                    map.put("id", user.getId());
                    map.put("username", user.getUsername());
                    map.put("email", user.getEmail());
                    map.put("role", user.getRole());

                    return map;
                })
                .toList();
    }
}
