package com.example.demo;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyCookieJar implements CookieJar {

    private List<Cookie> cookies;

    @Override
    public void saveFromResponse(@NotNull HttpUrl url, @NotNull List<Cookie> cookies) {
        this.cookies = cookies;
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl url) {
        if (cookies != null)
            return cookies;
        return new ArrayList<>();

    }
}
