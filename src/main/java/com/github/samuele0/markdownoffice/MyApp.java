package com.github.samuele0.markdownoffice;

import github.samuele0.plugins.Plugin;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ServiceLoader;

public class MyApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("Registered Plugins: "+ServiceLoader.load(Plugin.class).stream().count());
        System.setProperty("com.google.inject.internal.cglib.$experimental_asm7", "true");
        URL url= getClass().getResource("/fxml/mainVIew.fxml");
        System.out.println(url);
        Parent root = new MyFxmlLoader(url, ResourceBundle.getBundle("bundles/main")).load();
        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
