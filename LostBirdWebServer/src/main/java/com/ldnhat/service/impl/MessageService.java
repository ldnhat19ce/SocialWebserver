package com.ldnhat.service.impl;

import com.ldnhat.DAO.IMessageDAO;
import com.ldnhat.DAO.impl.MessageDAO;
import com.ldnhat.model.MessageModel;
import com.ldnhat.service.IMessageService;
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
import java.util.Iterator;
import java.util.List;

public class MessageService implements IMessageService {

    private IMessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }


    @Override
    public List<MessageModel> findByUserSenderAndUserReceiver(int messageFrom, int messageTo) {
        return messageDAO.findByUserSenderAndUserReceiver(messageFrom, messageTo);
    }

    @Override
    public String saveFileMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String test = "";
        ServletContext context = request.getSession().getServletContext();
        final String address = context.getRealPath("/img_message/");

        final int MaxMemorySize = 1024 * 1024 * 3;

        final int MaxRequestSize = 1024 * 1024 * 50;

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
                        e.printStackTrace();
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
}
