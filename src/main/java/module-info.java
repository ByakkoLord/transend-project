module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires java.net.http;
    requires okhttp3;
    requires annotations;
    requires com.squareup.moshi;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}