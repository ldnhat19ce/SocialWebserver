package com.ldnhat.Utils;

import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class NotificationUtils {

    private static String key = "AAAAeuo9j10:APA91bE3GHBUrwgq9beHi5bDrWAia5jUbPt1Y6iP9esZW6sB1_HO_i4iEduRKaCqYzL5F" +
            "RgOJ-fTQ9Yq4iSekG9wrLxcxNm7B2C1iwSuyXpaInY2XXz_qoHbo8kJKEdWpdEy-S32RVsg";

    public static void sendNotification(String notificationMessage, String tokenKey){
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");

        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "key="+key);

        JsonObject message = new JsonObject();

        message.addProperty("to", tokenKey);

        JsonObject notification = new JsonObject();
        notification.addProperty("title", "Thông báo");
        notification.addProperty("body", notificationMessage);

        message.add("notification", notification);

        post.setEntity(new StringEntity(message.toString(), "UTF-8"));

        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(httpResponse);
    }
}
