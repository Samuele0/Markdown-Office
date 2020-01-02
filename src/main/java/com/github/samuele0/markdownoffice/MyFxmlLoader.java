package com.github.samuele0.markdownoffice;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import javafx.fxml.FXMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MyFxmlLoader extends FXMLLoader {
    public MyFxmlLoader(URL location, ResourceBundle resources) {
        super(location, resources);
        Injector injector = Guice.createInjector(new MyModule());
        setControllerFactory(injector::getInstance);

    }

    private static class MyModule implements Module {
        Logger log = LogManager.getLogger();

        @Override
        public void configure(Binder binder) {
            bindConfiguration(binder);
        }

        private void bindConfiguration(Binder binder) {
            Properties properties= new Properties();
            configureDefaultProperties(properties);
            try {
                log.debug("Reading configuration File: "+Paths.get("../config.properties").toAbsolutePath().toString());
                properties.load(Files.newBufferedReader(Paths.get("../config.properties")));
            } catch (IOException e) {
                log.info("No configuration file found");
                log.debug(e);
            }

            Names.bindProperties(binder,properties);
        }

        private void configureDefaultProperties(Properties properties) {
            properties.setProperty("application.hellomessage","Default Message");
        }
    }
}
