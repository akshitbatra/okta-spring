package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import static java.net.http.HttpRequest.BodyPublishers.ofString;

@RestController
public class AuthController {
    @RequestMapping(value = "login/callback/", method = RequestMethod.GET)
    public String OktaLoginCallback(@RequestParam String code, HttpServletResponse response) throws IOException, InterruptedException, JSONException {
        if (code == null) {
            return "The Okta Code is None";
        }

        //  Move to a config file --- start
        String clientSecret = "LhGvMmhmdOg7vOEEiz6uJsnvDATt-VXtfg3epghy";
        String clientId = "0oa26nuqtet6pJypD5d7";
        String domainUrl = "dev-22214085.okta.com";
        String frontendRedirectionUrl = "https://okta-login-demo.netlify.app/login/callback";
        String oktaAuthEndpoint = String.format("https://%s/oauth2/default/v1/token", domainUrl);
        String backendRedirectUrl = "http://localhost:8080/login/callback/";
        //  Move to a config file --- end


        String authCredentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes("UTF-8"));
        String urlEncodedBody = String.format("grant_type=authorization_code&code=%s&redirect_uri=%s", code, backendRedirectUrl);

        JSONObject oktaAuthResponse = validateWithOKTA(oktaAuthEndpoint, authCredentials, urlEncodedBody);


        String redirect_endpoint = String.format("%s?token_type=%s&expires_in=%s&access_token=%s&scope=%s&id_token=%s",
                frontendRedirectionUrl,
                oktaAuthResponse.getString("token_type"),
                oktaAuthResponse.getString("expires_in"),
                oktaAuthResponse.getString("access_token"),
                oktaAuthResponse.getString("scope"),
                oktaAuthResponse.getString("id_token")
        );

        response.sendRedirect(redirect_endpoint);
        return "Success :)";
    }

    private JSONObject validateWithOKTA(String oktaAuthEndpoint, String authCredentials, String urlEncodedBody) throws IOException, InterruptedException, JSONException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(oktaAuthEndpoint))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("accept", "application/json")
                .header("authorization", "Basic " + authCredentials)
                .POST(ofString(urlEncodedBody))
                .build();

        HttpResponse<String> oktaHttpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new JSONObject(oktaHttpResponse.body());
    }
}
