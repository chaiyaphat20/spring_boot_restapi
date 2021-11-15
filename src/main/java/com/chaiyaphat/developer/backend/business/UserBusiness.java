package com.chaiyaphat.developer.backend.business;

import com.chaiyaphat.developer.backend.entity.User;
import com.chaiyaphat.developer.backend.exception.BaseException;
import com.chaiyaphat.developer.backend.exception.FileException;
import com.chaiyaphat.developer.backend.exception.UserException;
import com.chaiyaphat.developer.backend.mapper.UserMapper;
import com.chaiyaphat.developer.backend.model.MLoginRequest;
import com.chaiyaphat.developer.backend.model.MRegisterRequest;
import com.chaiyaphat.developer.backend.model.MRegisterResponse;
import com.chaiyaphat.developer.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserBusiness {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public MRegisterResponse register(MRegisterRequest request) throws UserException {
        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        //TODO:mapper
        MRegisterResponse model = userMapper.toRegisterResponse(user);
        return model;
    }

    public String login(MLoginRequest mLoginRequest) throws BaseException {
        //validate request

        //verify database
        Optional<User> userDb = userService.findByEmail(mLoginRequest.getEmail());
        if (userDb.isEmpty()) {
            //throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }

        User user = userDb.get();
        if (!userService.matchPassword(mLoginRequest.getPassword(), user.getPassword())) {
            //throw password incorrect
            throw UserException.loginFailPasswordInvalid();
        }

        //TODO: generate JWT
        String token = "123465";
        return token;
    }

    public String uploadProfilePicture(MultipartFile file) throws BaseException {

        //validate file
        if (file == null) {
            //throw error
            FileException.fileNull();
        }
        //validate file not than 1 MB = 1048576 byte
        if (file.getSize() > 1048576 * 2) {
            //throw error
            FileException.fileMaxSize();
        }
        String contentType = file.getContentType();
        if (contentType == null) {
            //throw error
            FileException.fileNull();
        }
        List<String> supportTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportTypes.contains(contentType)) {
            //throw error (unsupported)
            FileException.unsupported();
        }

        //TODO: Upload file File storage (AWS S3, SFTP)
        try {
            byte[] buffer = file.getBytes();
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "upload success!";
    }
}
