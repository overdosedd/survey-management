package com.example;

import com.example.survey.api.SurveyResources;
import com.example.survey.config.SurveyConfiguration;
import com.example.survey.oci.LoggingOCIClients;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class SurveyServiceApplication extends Application<SurveyConfiguration> {

    public static void main(String[] args) throws Exception {
        new SurveyServiceApplication().run(args);
    }
    @Override
    public void initialize(Bootstrap<SurveyConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SurveyConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SurveyConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }
    @Override
    public void run(SurveyConfiguration config, Environment env) throws Exception {
        final SurveyResources resource = new SurveyResources();
        env.jersey().register(resource);
    }
}
