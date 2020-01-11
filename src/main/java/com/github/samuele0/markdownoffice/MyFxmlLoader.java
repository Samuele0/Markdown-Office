package com.github.samuele0.markdownoffice;

import com.github.samuele0.markdownoffice.plugins.impl.PluginConfigurationImpl;
import com.google.inject.*;
import com.google.inject.Module;
import com.google.inject.name.Names;
import github.samuele0.plugins.Plugin;
import github.samuele0.plugins.PluginConfiguration;
import javafx.fxml.FXMLLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
            binder.bind(PluginConfiguration.class).to(PluginConfigurationImpl.class);
        }

        private void bindConfiguration(Binder binder) {
            Properties properties = new Properties();
            configureDefaultProperties(properties);
            try {
                log.debug("Reading configuration File: " + Paths.get("../config.properties").toAbsolutePath().toString());
                properties.load(Files.newBufferedReader(Paths.get("../config.properties")));
            } catch (IOException e) {
                log.info("No configuration file found");
                log.debug(e);
            }

            Names.bindProperties(binder, properties);
        }

        @Provides
        public Set<Plugin> getPlugins() {
            return ServiceLoader.load(Plugin.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toSet());

        }

        private void configureDefaultProperties(Properties properties) {
            properties.setProperty("application.hellomessage", "Default Message");
        }
    }
}
