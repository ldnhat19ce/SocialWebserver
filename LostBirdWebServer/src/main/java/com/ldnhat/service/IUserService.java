package com.ldnhat.service;

import com.ldnhat.model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IUserService {

    UserModel findByUsernameAndEmail(String username, String email);
    UserModel save(UserModel userModel);
    UserModel findByEmailAndPassword(String email, String password);
    UserModel findOne(Long id);
    List<UserModel> findByScreenName(String screenName);
    List<UserModel> findUserNotFollow(int userId);
    void updateUserUid(UserModel userModel);
    UserModel updateUser(UserModel userModel);
    String saveProfileImageUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    String saveProfileCoverUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    UserModel findUserByUsername(String username);
}
