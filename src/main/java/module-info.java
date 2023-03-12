module com.example.contacts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.contacts to javafx.fxml;
    exports com.example.contacts;
    exports com.example.contacts.DataModel;
    opens com.example.contacts.DataModel to javafx.fxml;
}