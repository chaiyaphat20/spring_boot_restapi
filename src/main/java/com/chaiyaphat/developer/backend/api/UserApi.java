package com.chaiyaphat.developer.backend.api;

import com.chaiyaphat.developer.backend.business.UserBusiness;
import com.chaiyaphat.developer.backend.exception.BaseException;
import com.chaiyaphat.developer.backend.model.MLoginRequest;
import com.chaiyaphat.developer.backend.model.MRegisterRequest;
import com.chaiyaphat.developer.backend.model.MRegisterResponse;
import com.chaiyaphat.developer.backend.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

//    //METHOD1
//    @Autowired
//    private TestBusiness business;

    //METHOD2 =>Constructor Injection
    //เร็วกกว่า ด้าน performace
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest mLoginRequest) throws BaseException {
        String response = business.login(mLoginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/send-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }
}
