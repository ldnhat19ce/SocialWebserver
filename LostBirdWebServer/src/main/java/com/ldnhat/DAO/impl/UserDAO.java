package com.ldnhat.DAO.impl;

import com.ldnhat.DAO.IUserDAO;
import com.ldnhat.mapper.impl.UserMapper;
import com.ldnhat.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public UserModel findByUsernameAndEmail(String username, String email) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u ");
        sql.append("WHERE u.username = ? AND u.email = ?");

        Object[] params = {username, email};
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), "", params);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public List<UserModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u");

        return query(sql.toString(), new UserMapper(), "");
    }

    //follower : người theo dõi bạn
    //following: người bạn đang theo dõi
    @Override
    public Long save(UserModel userModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append("users(role_id, username, email, password, screen_name, profile_image, profile_cover, ");
        sql.append("following, followers, bio, create_date) ");
        sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");

        Object[] params = {userModel.getRole().getId(), userModel.getUsername(),
                    userModel.getEmail(), userModel.getPassword(), userModel.getScreenName(),
                    userModel.getProfileImage(), userModel.getProfileCover(), 
                    userModel.getFollowing(), userModel.getFollower(), userModel.getBio(),
                    userModel.getCreateDate()};
        return insert(sql.toString(), params);
    }

    @Override
    public UserModel findOne(Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u WHERE u.user_id = ?");
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), "", id);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public UserModel findByEmailAndPassword(String email, String password) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u ");
        sql.append("WHERE u.email = ? AND u.password = ?");

        Object[] params = {email, password};
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), "", params);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public List<UserModel> findByScreenName(String screenName) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u ");
        sql.append("WHERE u.screen_name LIKE ?");

        Object[] params = {screenName+"%"};
        return query(sql.toString(), new UserMapper(), "", params);
    }

    @Override
    public List<UserModel> findUserNotFollow(int userId) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u ");
        sql.append("WHERE u.user_id != ? AND u.user_id NOT IN ");
        sql.append("(SELECT f.user_receive FROM follow f WHERE f.user_sender = ?) ");
        sql.append("AND u.role_id = 2 ORDER BY rand() LIMIT 7");

        Object[] params = {userId, userId};
        return query(sql.toString(), new UserMapper(), "", params);
    }

    @Override
    public void updateUserUID(UserModel userModel) {
        StringBuilder sql = new StringBuilder("UPDATE users u ");
        sql.append("SET u.uid = ? WHERE u.user_id = ?");

        Object[] params = {userModel.getUid(), userModel.getId()};

        update(sql.toString(), params);
    }

    @Override
    public void updateUser(UserModel userModel) {
        StringBuilder sql = new StringBuilder("UPDATE users u ");
        sql.append("SET u.profile_image = ?, u.profile_cover = ?, ");
        sql.append("u.screen_name = ?, u.bio = ?, u.country = ?" );
        sql.append("WHERE u.user_id = ?");

        Object[] params = {userModel.getProfileImage(), userModel.getProfileCover(),
                userModel.getScreenName(), userModel.getBio(), userModel.getCountry(),
                userModel.getId()
        };

        update(sql.toString(), params);
    }

    @Override
    public UserModel findUserByUsername(String username) {
        StringBuilder sql = new StringBuilder("SELECT * FROM users u ");
        sql.append("WHERE u.username = ?");

        Object[] params = {username};
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), "", params);
        return userModels.isEmpty() ? null : userModels.get(0);
    }
}
