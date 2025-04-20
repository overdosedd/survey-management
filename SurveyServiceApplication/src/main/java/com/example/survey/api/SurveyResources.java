package com.example.survey.api;

import com.codahale.metrics.annotation.Timed;
import com.example.survey.core.SurveyEntity;
import com.example.survey.oci.LoggingOCIClients;

import io.swagger.v3.oas.annotations.Operation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/surveys")
@Timed
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SurveyResources {
    private final LoggingOCIClients loggingOCIClients = new LoggingOCIClients();

    public SurveyResources() throws IOException {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "recieve survey data")
    public Response receiveSurvey(SurveyEntity data) {
        System.out.println("Survey id: " + data.getSurveyID());
        System.out.println("Score: " + data.getScore());
        System.out.println("Region: " + data.getRegion());
        System.out.println("Age: " + data.getAge());
        System.out.println("Gender: " + data.getGender());

        loggingOCIClients.sendLog(data);

        return Response.ok().build();
    }
}
