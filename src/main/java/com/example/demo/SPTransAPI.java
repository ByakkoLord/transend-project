package com.example.demo;
import io.github.cdimascio.dotenv.Dotenv;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.*;

import java.io.IOException;

public class SPTransAPI {
    private final static String apiUrl = "https://api.olhovivo.sptrans.com.br/v2.1";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<BusPosicaoResult> busPosicaoJsonAdapter = moshi.adapter(BusPosicaoResult.class);
    private final OkHttpClient client = new OkHttpClient.Builder().cookieJar(new MyCookieJar()).build();

    public SPTransAPI(String sptransKey) {
        RequestBody emptyBody = RequestBody.create("", null);
        Request request = new Request.Builder()
                .url(apiUrl + "/login/autenticar?token=" + sptransKey)
                .post(emptyBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();

            Dotenv dotenv = Dotenv.load();
            Database consult = new Database(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASS"));



            consult.sendBus(3, "132", "true", 1, 1, 1, 1);

            if (!response.isSuccessful() || body == null || !body.string().equals("true")) {
                throw new Error("Falha ao autenticar com api sptrans");
            }
        } catch (IOException e) {
            System.out.println("error: " + e);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }
    }

    public BusPosicaoResult getAllBuses() {
        Request request = new Request.Builder()
                .url(apiUrl + "/posicao")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new Error("getAllBuses Request failed");

            ResponseBody body = response.body();

            if (body == null) throw new Error("Falha ao autenticar com api sptrans");

            return busPosicaoJsonAdapter.fromJson(body.source());
        } catch (IOException e) {
            System.out.println("error: " + e);
        } catch (Error e) {
            System.out.println(e.getMessage());
        }




        return null;
    }
}
