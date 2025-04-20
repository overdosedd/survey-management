package com.example.samplegenerator;

import core.SurveyEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SampleGeneratorApplication {

		private static final String ENDPOINT_URL = "http://localhost:8080/surveys"; // update if different
		private static final ObjectMapper mapper = new ObjectMapper();
		private static final Random random = new Random();

		public static void main(String[] args) throws Exception {
			for (int i = 0; i < 10; i++) {
				SurveyEntity sample = generateRandomSurvey();
				sendSample(sample);
				Thread.sleep(1000); // 1 second between requests
			}
		}

		private static SurveyEntity generateRandomSurvey() {
			String[] genders = {"Male", "Female", "Other"};
			String[] regions = {"APAC", "EMEA", "AMER"};

			SurveyEntity entity = new SurveyEntity();
			entity.setSurveyID("survey-" + random.nextInt(1000));
			entity.setAge(random.nextInt(60) + 18);
			entity.setGender(genders[random.nextInt(genders.length)]);
			entity.setRegion(regions[random.nextInt(regions.length)]);
			entity.setScore(random.nextInt(11)); // 0-10

			return entity;
		}

		private static void sendSample(SurveyEntity sample) throws Exception {
			URL url = new URL(ENDPOINT_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);

			String jsonInput = mapper.writeValueAsString(sample);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInput.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int code = con.getResponseCode();
			System.out.println("Sent sample with status: " + code);
		}

}
