package com.ldnhat.DAO;

import com.ldnhat.model.UserModel;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserModel>{

    UserModel findByUsernameAndEmail(String username, String email);
    List<UserModel> findAll();
    Long save(UserModel userModel);
    UserModel findOne(Long id);
    UserModel findByEmailAndPassword(String email, String password);
    List<UserModel> findByScreenName(String screenName);
    List<UserModel> findUserNotFollow(int userId);
    void updateUserUID(UserModel userModel);
    void updateUser(UserModel userModel);
    UserModel findUserByUsername(String username);
}
