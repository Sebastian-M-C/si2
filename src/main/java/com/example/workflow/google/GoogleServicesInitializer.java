//package com.example.workflow.google;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.services.docs.v1.Docs;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collections;
//
//public class GoogleServicesInitializer {
//    private static final String APPLICATION_NAME = "Your App Name";
//
//    public static Docs getDocsService() throws IOException {
//        InputStream credentialsStream = GoogleServicesInitializer.class.getResourceAsStream("/google-credentials.json");
//
//        GoogleCredential credential = GoogleCredential.fromStream(credentialsStream)
//                .createScoped(Collections.singleton(DriveScopes.DRIVE));
//
//        return new Docs.Builder(
//                credential.getTransport(),
//                credential.getJsonFactory(),
//                credential
//        ).setApplicationName(APPLICATION_NAME).build();
//    }
//
//    public static Drive getDriveService() throws IOException {
//        InputStream credentialsStream = GoogleServicesInitializer.class.getResourceAsStream("/google-credentials.json");
//
//        GoogleCredential credential = GoogleCredential.fromStream(credentialsStream)
//                .createScoped(Collections.singleton(DriveScopes.DRIVE));
//
//        return new Drive.Builder(
//                credential.getTransport(),
//                credential.getJsonFactory(),
//                credential
//        ).setApplicationName(APPLICATION_NAME).build();
//    }
//
//}
