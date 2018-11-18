package com.lyricxinc.lyricx.core.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseConfig {

    public static FirebaseApp adminFirebaseApp;
    public static FirebaseApp contributorFirebaseApp;
    public static FirebaseApp chanterFirebaseApp;

    @EventListener(ApplicationReadyEvent.class)
    public void configureFirebaseAdminSDK() throws IOException {

        File adminFile = ResourceUtils.getFile("classpath:static/lyricx-admin-firebase-adminsdk.json");
        InputStream adminServiceAccount = new FileInputStream(adminFile);
        FirebaseOptions adminOptions = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(adminServiceAccount))
                .build();

        adminFirebaseApp = FirebaseApp.initializeApp(adminOptions, "admin");

        File contributorFile = ResourceUtils.getFile("classpath:static/lyricx-contributor-firebase-adminsdk.json");
        InputStream contributorServiceAccount = new FileInputStream(contributorFile);
        FirebaseOptions contributorOptions = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(contributorServiceAccount))
                .build();

        contributorFirebaseApp = FirebaseApp.initializeApp(contributorOptions, "contributor");

        File chanterFile = ResourceUtils.getFile("classpath:static/lyricx-chanter-firebase-adminsdk.json");
        InputStream chanterServiceAccount = new FileInputStream(chanterFile);
        FirebaseOptions chanterOptions = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(chanterServiceAccount))
                .build();

        chanterFirebaseApp = FirebaseApp.initializeApp(chanterOptions, "chanter");
    }

}
