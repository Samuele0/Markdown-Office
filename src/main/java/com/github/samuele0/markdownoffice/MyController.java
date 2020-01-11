package com.github.samuele0.markdownoffice;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import github.samuele0.plugins.Plugin;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class MyController implements Initializable {
    Logger log = LogManager.getLogger();

    @Inject
    public MyController(Set<Plugin> plugins) {
        log.info(plugins.stream().findFirst().get().getClass().getCanonicalName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
