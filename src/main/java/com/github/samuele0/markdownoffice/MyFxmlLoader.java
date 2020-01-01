package com.github.samuele0.markdownoffice;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.util.ResourceBundle;

public class MyFxmlLoader extends FXMLLoader {
    public MyFxmlLoader(URL location, ResourceBundle resources) {
        super(location, resources);
        Injector injector = Guice.createInjector(new MyModule());
        setControllerFactory(injector::getInstance);

    }

    private static class MyModule implements Module {
        @Override
        public void configure(Binder binder) {
            binder.bind(String.class).toInstance("CIao UNiverso!");
        }
    }
}
