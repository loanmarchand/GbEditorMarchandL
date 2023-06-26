module org.helmo {
    requires javafx.controls;
    requires java.desktop;
    requires javafx.graphics;
    requires com.google.gson;
    exports org.helmo.gbeditor;
    opens org.helmo.gbeditor.model to com.google.gson;
}