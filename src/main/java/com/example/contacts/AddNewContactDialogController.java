package com.example.contacts;

import com.example.contacts.DataModel.ContactListItems;
import com.example.contacts.DataModel.Contacts;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddNewContactDialogController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;

    public Contacts processResults()
    {
        String firstNameText = firstName.getText();
        String middleNameText = middleName.getText();
        String lastNameText = lastName.getText();
        String emailText = email.getText();
        String phoneNumberText = phoneNumber.getText();

        Contacts newContact = new Contacts(firstNameText,middleNameText,lastNameText,emailText,phoneNumberText);
        ContactListItems.getInstance().addContact(newContact);
        return newContact;
    }

}
