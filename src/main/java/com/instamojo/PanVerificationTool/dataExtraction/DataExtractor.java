package com.instamojo.PanVerificationTool.dataExtraction;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class DataExtractor {
	private String imageJson;
	private String textJson;
	private String data;
	private static final String uriBase = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0/";
	private static final String subscriptionKey = "32075a7636b941048a92976ebb821517";

	public DataExtractor(String data) {
		this.data = data;
		imageJson = extractImageDetails();
		textJson = extractTextDetails();
	}

	private String extractTextDetails() {
		String text = null;
		HttpClient httpClient = new DefaultHttpClient();

		try {
			// NOTE: You must use the same location in your REST call as you used to obtain
			// your subscription keys.
			// For example, if you obtained your subscription keys from westus, replace
			// "westcentralus" in the
			// URL below with "westus".
			URIBuilder uriBuilder = new URIBuilder(uriBase);

			uriBuilder.setParameter("language", "unk");
			uriBuilder.setParameter("detectOrientation ", "true");

			// Request parameters.
			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			StringEntity requestEntity = new StringEntity(
					"{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Atomist_quote_from_Democritus.png/338px-Atomist_quote_from_Democritus.png\"}");
			request.setEntity(requestEntity);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Format and display the JSON response.
				String jsonString = EntityUtils.toString(entity);
				JSONObject json = new JSONObject(jsonString);
				System.out.println("REST Response:\n");
				System.out.println(json.toString(2));
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}

		return text;
	}

	private String extractImageDetails() {
		String image = null;
		HttpClient httpclient = new DefaultHttpClient();

		try {
			URIBuilder builder = new URIBuilder(uriBase);

			// Request parameters. All of them are optional.
			builder.setParameter("visualFeatures", "Categories,Description,Color");
			builder.setParameter("language", "en");

			// Prepare the URI for the REST API call.
			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			// Request headers.
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body..
			StringEntity reqEntity = new StringEntity(
					"{\"url\":\"https://upload.wikimedia.org/wikipedia/commons/1/12/Broadway_and_Times_Square_by_night.jpg\"}");
			request.setEntity(reqEntity);

			// Execute the REST API call and get the response entity.
			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Format and display the JSON response.
				String jsonString = EntityUtils.toString(entity);
				JSONObject json = new JSONObject(jsonString);
				System.out.println("REST Response:\n");
				System.out.println(json.toString(2));
			}
		} catch (Exception e) {
			// Display error message.
			System.out.println(e.getMessage());
		}
		return image;
	}

	public String getImageJson() {
		return imageJson;
	}

	public String getTextDetails() {
		return textJson;
	}

	public static void main(String args[]) {

	}
}
