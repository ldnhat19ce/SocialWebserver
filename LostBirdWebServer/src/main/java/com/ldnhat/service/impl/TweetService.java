package com.ldnhat.service.impl;

import com.ldnhat.DAO.ITweetDAO;
import com.ldnhat.DAO.impl.TweetDAO;
import com.ldnhat.model.TweetModel;
import com.ldnhat.service.ITweetService;
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

public class TweetService implements ITweetService {

    private ITweetDAO tweetDAO;
    public TweetService() {
        tweetDAO = new TweetDAO();
    }

    @Override
    public List<TweetModel> tweet(int userId){
        return tweetDAO.tweet(userId);
    }

    @Override
    public String saveFileTweet(HttpServletRequest request, HttpServletResponse response, TweetModel tweetModel)
            throws ServletException, IOException {
        String test = "";
        ServletContext context = request.getSession().getServletContext();
        final String address = context.getRealPath("/image_tweet/");

        final int MaxMemorySize = 1024 * 1024 * 3;

        final int MaxRequestSize = 1024 * 1024 * 50;

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            test = "thiếu enctype: multipart/form-data";

        }
        System.out.println(test);
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

//                        if (kt == true) {
//                            test = "file đã tồn tại";
//                        } else {
//
//                        }

                        item.write(uploadedFile);
                        System.out.println("file name: " + fileName);
                        System.out.println("tweet id: "+tweetModel.getId());
                        System.out.println("user id: "+tweetModel.getUserModel().getId());
                        System.out.println("tweet status: "+tweetModel.getTweetStatus());
                        tweetModel.setTweetImage(fileName);
                        update(tweetModel);
                        test = "success";

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
    public TweetModel save(TweetModel tweetModel) {
        Long tweetId = tweetDAO.save(tweetModel);
        return tweetDAO.findOne(tweetId);
    }

    @Override
    public TweetModel findOne(Long id) {
        return tweetDAO.findOne(id);
    }

    @Override
    public TweetModel update(TweetModel tweetModel) {
        tweetDAO.update(tweetModel);
        return tweetDAO.findOne((long) tweetModel.getId());
    }

    @Override
    public void updateTweetStatus(TweetModel tweetModel) {
        tweetDAO.updateTweetStatus(tweetModel);
    }
}
