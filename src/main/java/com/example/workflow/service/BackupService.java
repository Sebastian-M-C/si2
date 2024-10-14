package com.example.workflow.service;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class BackupService {

    private final JdbcTemplate jdbcTemplate;

    public BackupService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void backupTableToCSV(String tableName, String outputFilePath) {
        String query = "SELECT * FROM " + tableName;

        // Ejecutar la consulta
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
            // Escribir encabezados de columnas
            if (!rows.isEmpty()) {
                Map<String, Object> firstRow = rows.get(0);
                fileWriter.append(String.join(",", firstRow.keySet()));
                fileWriter.append("\n");

                // Escribir datos
                for (Map<String, Object> row : rows) {
                    for (Object value : row.values()) {
                        fileWriter.append(value != null ? value.toString() : "");
                        fileWriter.append(",");
                    }
                    fileWriter.append("\n");
                }
            }

            System.out.println("Backup completado en: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
