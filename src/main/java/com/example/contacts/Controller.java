package com.example.contacts;

import com.example.contacts.DataModel.ContactListItems;
import com.example.contacts.DataModel.Contacts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<Contacts> contacts = new ArrayList<Contacts>();
    @FXML
    private ListView contactListView;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize()
    {
        contacts.add(new Contacts("Mahesh","Kumar","Singh","mahesh.23vt@gmail.com","7996598994"));
        contactListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contacts>() {
            @Override
            public void changed(ObservableValue<? extends Contacts> observableValue, Contacts oldValue, Contacts newValue) {
                if (newValue != null) {
                    Contacts item = (Contacts) contactListView.getSelectionModel().getSelectedItem();
                    contactListView.setItems(ContactListItems.getInstance().getContacts());
                    contactListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    contactListView.getSelectionModel().selectFirst();

                }
            }
        });
    }
    public void showAddNewContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        dialog.setHeaderText("Enter the contact details here");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddNewContactDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            AddNewContactDialogController controller = fxmlLoader.getController();
            Contacts newContact = controller.processResults();
            contactListView.getSelectionModel().select(newContact);

        }

    }
}