open module markdownoffice {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires com.google.guice;
    requires markdownoffice.pluginsbase;
    uses github.samuele0.plugins.Plugin;
    exports com.github.samuele0.markdownoffice;
}