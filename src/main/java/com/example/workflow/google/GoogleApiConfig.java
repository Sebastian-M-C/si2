//package com.example.workflow.google;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.docs.v1.Docs;
//import com.google.api.services.drive.Drive;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.imageio.spi.IIORegistry;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.Collections;
//
//@Configuration
//public class GoogleApiConfig {
//
//    private static final String APPLICATION_NAME = "GoogleDocsAPIIntegration";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance(); // Usa JacksonFactory
//    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/google-credentials.json"; // Ruta al archivo de credenciales
//
//    @Bean
//    public Docs googleDocsService() throws Exception {
//        GoogleCredential credential = getGoogleCredential();
//        return new Docs.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JSON_FACTORY,
//                credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    @Bean
//    public Drive googleDriveService() throws Exception {
//        GoogleCredential credential = getGoogleCredential();
//        return new Drive.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JSON_FACTORY,
//                credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    private GoogleCredential getGoogleCredential() throws Exception {
//        InputStream credentialsStream = new FileInputStream(CREDENTIALS_FILE_PATH);
//        return GoogleCredential.fromStream(credentialsStream)
//                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/drive"));
//    }
//}
