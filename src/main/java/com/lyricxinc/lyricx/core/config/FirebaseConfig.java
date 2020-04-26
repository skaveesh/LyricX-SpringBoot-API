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

/**
 * The type Firebase config.
 */
@Service
public class FirebaseConfig {

    private static FirebaseApp adminFirebaseApp;
    private static FirebaseApp contributorFirebaseApp;
    private static FirebaseApp chanterFirebaseApp;

    /**
     * Configure firebase admin sdk.
     *
     * @throws IOException the io exception
     */
    @EventListener(ApplicationReadyEvent.class)
    public void configureFirebaseAdminSDK() throws IOException {

        File adminFile = ResourceUtils.getFile("classpath:static/lyricx-admin-firebase-adminsdk.json");
        InputStream adminServiceAccount = new FileInputStream(adminFile);
        FirebaseOptions adminOptions = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(adminServiceAccount)).build();

        adminFirebaseApp = FirebaseApp.initializeApp(adminOptions, "admin");

        File contributorFile = ResourceUtils.getFile("classpath:static/lyricx-contributor-firebase-adminsdk.json");
        InputStream contributorServiceAccount = new FileInputStream(contributorFile);
        FirebaseOptions contributorOptions = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(contributorServiceAccount)).build();

        contributorFirebaseApp = FirebaseApp.initializeApp(contributorOptions, "contributor");

        File chanterFile = ResourceUtils.getFile("classpath:static/lyricx-chanter-firebase-adminsdk.json");
        InputStream chanterServiceAccount = new FileInputStream(chanterFile);
        FirebaseOptions chanterOptions = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(chanterServiceAccount)).build();

        chanterFirebaseApp = FirebaseApp.initializeApp(chanterOptions, "chanter");

    }

    /**
     * Gets admin firebase app.
     *
     * @return the admin firebase app
     */
    public static FirebaseApp getAdminFirebaseApp() {

        return adminFirebaseApp;
    }

    /**
     * Gets contributor firebase app.
     *
     * @return the contributor firebase app
     */
    public static FirebaseApp getContributorFirebaseApp() {

        return contributorFirebaseApp;
    }

    /**
     * Gets chanter firebase app.
     *
     * @return the chanter firebase app
     */
    public static FirebaseApp getChanterFirebaseApp() {

        return chanterFirebaseApp;
    }

}
