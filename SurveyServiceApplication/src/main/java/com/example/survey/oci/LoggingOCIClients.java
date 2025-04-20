package com.example.survey.oci;
import com.example.survey.core.SurveyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.loggingingestion.LoggingClient;
import com.oracle.bmc.loggingingestion.model.*;
import com.oracle.bmc.loggingingestion.requests.PutLogsRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class LoggingOCIClients {
    private final LoggingClient client;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String logId = "ocid1.log.oc1.ap-singapore-1.amaaaaaayjewd5yaqtbu7ugnjc45wcskxemkrbmok6pszfirdv2zvyxfho2q";

    public LoggingOCIClients() throws IOException {
//        String configPath = Paths.get(System.getProperty("user.home"), ".oci", "config").toString();
        System.out.println("Config path: " + new File("C:/oci/config").getAbsolutePath());
         ConfigFileAuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider("C:/.oci/config", "DEFAULT");
        this.client = LoggingClient.builder().region(Region.AP_SINGAPORE_1).build(provider);
    }

    public void sendLog(SurveyEntity data) {
        try {
            String json = mapper.writeValueAsString(data);
            ZonedDateTime zdt = ZonedDateTime.now();
            Instant instant = zdt.toInstant();
            Date date = Date.from(instant);
            LogEntry logEntry = LogEntry.builder()
                    .id(UUID.randomUUID().toString())
                    .data(json)
                    .build();

            PutLogsDetails logsDetails = PutLogsDetails.builder()
                    .specversion("1.0")
                    .logEntryBatches(Collections.singletonList(
                            LogEntryBatch.builder()
                                    .entries(Collections.singletonList(logEntry))
                                    .source("SurveyAPI")
                                    .type("SurveyLog")
                                    .defaultlogentrytime(date)  // Use the formatted date
                                    .build()
                    ))
                    .build();
            PutLogsRequest request = PutLogsRequest.builder()
                    .logId(logId)
                    .putLogsDetails(logsDetails)
                    .build();

            client.putLogs(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
