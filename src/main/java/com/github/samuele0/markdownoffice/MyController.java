package com.github.samuele0.markdownoffice;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import github.samuele0.plugins.Plugin;
import github.samuele0.plugins.PluginConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MyController implements Initializable {
    Logger log = LogManager.getLogger();

    @FXML
    private HBox notificationArea;


    @Inject
    public MyController(Set<Plugin> plugins, PluginConfiguration configuration) {
        plugins.forEach(plugin -> {
            log.info("Found plugin: " + plugin.getClass().getName());
            plugin.configure(configuration);
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
