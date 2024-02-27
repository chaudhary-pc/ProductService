package org.example.productservice.commons;
import org.example.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AuthenticationCommons {
    private RestTemplate restTemplate;
    public AuthenticationCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public UserDto validateToken(String token){
        ResponseEntity<UserDto> userResponseEntity = restTemplate.postForEntity(
                "http://localhost:8081/user/validate/"+ token,
                null,
                UserDto.class);
        if(userResponseEntity.getBody() == null) return null;
        return userResponseEntity.getBody();
    }
}
