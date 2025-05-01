module com.fp.finalproject.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fp.finalproject.finalproject to javafx.fxml;
    exports com.fp.finalproject.finalproject;
}