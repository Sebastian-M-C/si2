package com.example.workflow.controller;

import com.example.workflow.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackupController {

    @Autowired
    public BackupService backupService;

    @GetMapping("/backup")
    public String backup(){
        // Ruta donde se guardará el archivo CSV
        String backupPath = "C:/backups/mi_tabla_backup.csv";

        // Nombre de la tabla a respaldar
        String tableName = "cliente";

        // Ejecutar el backup
        backupService.backupTableToCSV(tableName, backupPath);

        return "Backup realizado en: " + backupPath;
    }
}
