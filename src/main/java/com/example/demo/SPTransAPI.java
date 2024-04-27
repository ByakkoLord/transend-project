package com.example.demo;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

public class SPTransAPI {
    private final static String apiUrl = "https://api.olhovivo.sptrans.com.br/v2.1/";
    private CloseableHttpClient httpClient;

    public SPTransAPI(String sptransKey) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            this.httpClient = httpClient;

            ClassicHttpRequest httpPost = ClassicRequestBuilder.post(apiUrl + "/Login/Autenticar")
                    .setEntity(new UrlEncodedFormEntity(List.of(
                            new BasicNameValuePair("token", sptransKey)
                    )))
                    .build();

            String result = httpClient.execute(httpPost, response -> {
               System.out.println(response.getCode() + " | " + response.getReasonPhrase());

                final HttpEntity entity = response.getEntity();

                EntityUtils.consume(entity);
                return EntityUtils.toString(response.getEntity());
            });

            System.out.println("sptrans respondeu " + result);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Bus[] getBusOnCorridor() {
        try {
            ClassicRequestBuilder httpPost = ClassicRequestBuilder.post(apiUrl + "")

        }
    }

    public static class Bus {
        public String id;
        public String corredor;
    }
}
