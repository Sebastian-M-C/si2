package com.example.workflow.controller;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.*;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


//@Controller
//@RequestMapping("/docs")
//public class GoogleDocsController {
//
//    @Autowired
//    private Docs googleDocsService;
//
//    @Autowired
//    private Drive googleDriveService;
//
//    @GetMapping("/load")
//    public String loadDocument(@RequestParam("documentId") String documentId, Model model) {
//        try {
//            // Obtener el contenido del documento
//            String content = googleDocsService.documents().get(documentId).execute().toString();
//            model.addAttribute("documentId", documentId);
//            model.addAttribute("documentContent", content);
//        } catch (Exception e) {
//            model.addAttribute("error", "Error loading document: " + e.getMessage());
//        }
//        return "google/editDocument"; // Nombre del HTML
//    }
//
//    @GetMapping("/edit")
//    public String editGoogleDocument(@RequestParam("documentId") String documentId, Model model) {
//        try {
//            // Obtener el contenido del documento de Google Docs
//            var document = googleDocsService.documents().get(documentId).execute();
//
//            // Pasar los datos a la vista
//            model.addAttribute("documentId", documentId);
//            model.addAttribute("documentContent", document.toPrettyString());
//        } catch (Exception e) {
//            model.addAttribute("error", "Error loading document: " + e.getMessage());
//        }
//        return "google/editDocument"; // HTML para editar el documento
//    }
//
//    @GetMapping("/")
//    public String home(Model model) {
//        try {
//            var fileList = googleDriveService.files().list()
//                    .setQ("mimeType='application/vnd.google-apps.document'")
//                    .setFields("files(id, name)")
//                    .execute();
//
//            model.addAttribute("documents", fileList.getFiles());
//        } catch (Exception e) {
//            model.addAttribute("error", "Error fetching documents: " + e.getMessage());
//        }
//        return "index"; // Cambia por la vista de tu página principal
//    }
//
//
//    @PostMapping("/edit")
//    public String updateGoogleDocument(@RequestParam("documentId") String documentId,
//                                       @RequestParam("content") String content,
//                                       Model model) {
//        try {
//            // Aquí puedes implementar la lógica para actualizar el documento usando Google Docs API
//            // Por ejemplo, agregar contenido al documento
//
//            // Confirmar actualización
//            model.addAttribute("message", "Document updated successfully!");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error updating document: " + e.getMessage());
//        }
//        return "google/editDocument";
//    }
//
//    @PostMapping("/update")
//    public String updateDocument(@RequestParam("documentId") String documentId,
//                                 @RequestParam("content") String content,
//                                 Model model) {
//        try {
//            // Crear una solicitud de actualización para el documento
//            var requests = new ArrayList<Request>();
//            requests.add(new Request().setInsertText(new InsertTextRequest()
//                    .setText(content)
//                    .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//            googleDocsService.documents().batchUpdate(documentId, new BatchUpdateDocumentRequest().setRequests(requests)).execute();
//
//            model.addAttribute("message", "Document updated successfully!");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error updating document: " + e.getMessage());
//        }
//        return "google/editDocument"; // Nombre del HTML
//    }
//
//    @GetMapping("/list")
//    public String listGoogleDocs(Model model) {
//        try {
//            // Listar archivos en Google Drive con la extensión .gdoc
//            var fileList = googleDriveService.files().list()
//                    .setQ("mimeType='application/vnd.google-apps.document'")
//                    .setFields("files(id, name)")
//                    .execute();
//
//            // Pasar los archivos a la vista
//            model.addAttribute("documents", fileList.getFiles());
//        } catch (Exception e) {
//            model.addAttribute("error", "Error fetching documents: " + e.getMessage());
//        }
//        return "google/listDocuments"; // HTML para mostrar los documentos
//    }
//    //Importante
//
//    @GetMapping("/create")
//    public String redireccionar(){
//        return "google/createDocument";
//    }
//
//    @PostMapping("/create")
//    public String createGoogleDocument(@RequestParam("title") String title, Model model) {
//        try {
//            // Crear el documento en Google Docs
//            var document = new com.google.api.services.docs.v1.model.Document();
//            document.setTitle(title);
//            var createdDocument = googleDocsService.documents().create(document).execute();
//
//            // Obtener el ID del documento creado
//            String documentId = createdDocument.getDocumentId();
//
//            // Agregar contenido inicial al documento
//            addContentToDocument(documentId);
//
//            // Configurar permisos públicos para el documento
//            setPublicPermissions(documentId);
//
//            // Generar la URL para abrir el documento
//            String documentUrl = "https://docs.google.com/document/d/" + documentId + "/edit";
//
//            // Pasar información a la vista
//            model.addAttribute("message", "Document created successfully!");
//            model.addAttribute("documentUrl", documentUrl);
//        } catch (Exception e) {
//            model.addAttribute("error", "Error creating document: " + e.getMessage());
//        }
//        return "google/createDocument"; // Vista Thymeleaf
//    }
//
//
//
//    private void addContentToDocument(String documentId) throws Exception {
//        // Lista de solicitudes para agregar contenido al documento
//        var requests = new ArrayList<Request>();
//
//        // Agregar un encabezado
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("Título: Acta de Entrega\n")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        // Agregar un párrafo de texto
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("Mediante la presenta Acta para seguimiento de su predio se " +
//                        "dara a conocer todos los detalles.\n\n\n\n")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        // Agregar una lista numerada
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("1. Título\n2. Matrícula\n 3. Beneficiario(s)\n\n\n\n")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        // Agregar una lista numerada
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("Provincia\n Dimensiones\n Departamento\n\n")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("Firma Empleado                Firma Beneficiario\n")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        // Agregar un pie de página
//        requests.add(new Request().setInsertText(new InsertTextRequest()
//                .setText("Footer: Creado automaticamente por nuestro sistema ")
//                .setEndOfSegmentLocation(new EndOfSegmentLocation())));
//
//        // Aplicar las solicitudes al documento
//        googleDocsService.documents().batchUpdate(
//                documentId,
//                new BatchUpdateDocumentRequest().setRequests(requests)
//        ).execute();
//    }
//
//    // Método para configurar permisos públicos
//    private void setPublicPermissions(String documentId) throws Exception {
//        Permission permission = new Permission();
//        permission.setType("anyone"); // Acceso público
//        permission.setRole("writer"); // Permitir edición
//
//        // Aplicar los permisos utilizando Google Drive API
//        googleDriveService.permissions().create(documentId, permission).execute();
//    }
//
//
//
//}
