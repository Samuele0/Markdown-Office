package com.github.samuele0.markdownoffice;

import com.google.inject.Inject;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    Logger log = LogManager.getLogger();

    @Inject
    public MyController(String s) {
        log.error(s);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
