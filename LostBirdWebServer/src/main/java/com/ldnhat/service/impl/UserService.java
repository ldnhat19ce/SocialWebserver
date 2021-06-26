package com.ldnhat.service.impl;

import com.ldnhat.DAO.IUserDAO;
import com.ldnhat.DAO.impl.UserDAO;
import com.ldnhat.model.UserModel;
import com.ldnhat.service.IUserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class UserService implements IUserService {

    private IUserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public List<UserModel> findAll(){
        return userDAO.findAll();
    }

    @Override
    public UserModel findByUsernameAndEmail(String username, String email){
        return userDAO.findByUsernameAndEmail(username, email);
    }

    @Override
    public UserModel save(UserModel userModel){
        userModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        Long userId = userDAO.save(userModel);
        return userDAO.findOne(userId);
    }
    @Override
    public UserModel findByEmailAndPassword(String email, String password){
        return userDAO.findByEmailAndPassword(email, password);
    }

    @Override
    public UserModel findOne(Long id){
        return userDAO.findOne(id);
    }

    @Override
    public List<UserModel> findByScreenName(String screenName) {
        return userDAO.findByScreenName(screenName);
    }

    @Override
    public List<UserModel> findUserNotFollow(int userId) {
        return userDAO.findUserNotFollow(userId);
    }

    @Override
    public void updateUserUid(UserModel userModel) {
        userDAO.updateUserUID(userModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        userDAO.updateUser(userModel);
        return userDAO.findOne((long) userModel.getId());
    }

    @Override
    public String saveProfileImageUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String test = "";
        ServletContext context = request.getSession().getServletContext();
        final String address = context.getRealPath("/image_user/");

        final int MaxMemorySize = 1024 * 1024 * 3;

        final int MaxRequestSize = 1024 * 1024 * 50;

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            test = "thiếu enctype: multipart/form-data";

        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(MaxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MaxRequestSize);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);


            for (FileItem item : items){
                System.out.println(item.getName());
            }

            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (!item.isFormField()) {

                    String fileName = item.getName();

                    // pathfile: vị trí mà chúng ta muốn upload file vào
                    // gửi cho server

                    String pathFile = address + File.separator + fileName;

                    File uploadedFile = new File(pathFile);
                    boolean kt = uploadedFile.exists();

                    try {
                        item.write(uploadedFile);

                        test = fileName;
                        System.out.println("test_file_name "+test);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        test = e.getMessage();
                        System.out.println(test);
                    }

                } else {
                    test = "failed";
                }
            }

        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public String saveProfileCoverUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String test = "";
        ServletContext context = request.getSession().getServletContext();
        final String address = context.getRealPath("/image_user/");

        final int MaxMemorySize = 1024 * 1024 * 3;

        final int MaxRequestSize = 1024 * 1024 * 50;

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            test = "thiếu enctype: multipart/form-data";

        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(MaxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MaxRequestSize);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);


            for (FileItem item : items){
                System.out.println(item.getName());
            }

            // Process the uploaded items
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (!item.isFormField()) {

                    String fileName = item.getName();

                    // pathfile: vị trí mà chúng ta muốn upload file vào
                    // gửi cho server

                    String pathFile = address + File.separator + fileName;

                    File uploadedFile = new File(pathFile);
                    boolean kt = uploadedFile.exists();

                    try {
                        item.write(uploadedFile);

                        test = fileName;

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        test = e.getMessage();
                        System.out.println(test);
                    }

                } else {
                    test = "failed";
                }
            }

        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public UserModel findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }
}
