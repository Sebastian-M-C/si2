package com.example.workflow.component;

import com.example.workflow.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledBackupTask {

    @Autowired
    private BackupService backupService;

    @Scheduled(cron = "0 */5 * * * ?") // Cada 5 minutos
    public void performBackup() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupPath = "C:/backups/mi_tabla_backup" + timestamp + ".csv";
        String tableName = "cliente";
        backupService.backupTableToCSV(tableName, backupPath);
        System.out.println("Backup realizado a las: " + timestamp);
    }

}
