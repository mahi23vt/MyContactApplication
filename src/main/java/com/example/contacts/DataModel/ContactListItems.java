package com.example.contacts.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class ContactListItems {
    // Instance created
    private static ContactListItems instance = new ContactListItems();
    // create the file location name
    private static String fileName="myContacts.txt";
    private ObservableList<Contacts> contacts;
    public static ContactListItems getInstance()
    {
        return instance;
    }
    public ObservableList<Contacts> getContacts()
    {
        return contacts;
    }
    // Loading contacts
    public void loadContacts() throws IOException
    {
        contacts= FXCollections.observableArrayList();
        Path path= Paths.get(fileName); // nio
        BufferedReader br = Files.newBufferedReader(path);
        String input;
        try
        {
            while((input = br.readLine()) != null)
            {
                String[] itemPieces = input.split("\t");
                String firstName = itemPieces[0];
                String middleName = itemPieces[1];
                String lastName = itemPieces[2];
                String email = itemPieces[3];
                String phoneNumber = itemPieces[4];
                Contacts contact = new Contacts(firstName,middleName, lastName, email,phoneNumber);
                contacts.add(contact);
            }
        }
        finally {
            if(br!= null)
                br.close();
        }


    }
    public void storeContacts()  throws  IOException
    {
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try
        {
            Iterator<Contacts> iter = contacts.iterator();
            while(iter.hasNext())
            {
                Contacts contactItem = iter.next();
                bw.write(String.format("%s\t%s\t%s\t%s\t%s",
                        contactItem.getFirstName(),
                        contactItem.getMiddleName(),
                        contactItem.getLastName(),
                        contactItem.getEmail(),
                        contactItem.getPhoneNumber()));
                bw.newLine();
            }
        }
        finally {
            if(bw!=null)
                bw.close();
        }
    }
    public void addContact(Contacts newContact)
    {
        contacts.add(newContact);
    }
    public void deleteContact(Contacts toDeleteContact)
    {
        contacts.remove(toDeleteContact);
    }

}
