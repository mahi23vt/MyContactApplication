package com.example.contacts;

import com.example.contacts.DataModel.ContactListItems;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 240);
        stage.setTitle("My Contacts");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public void stop()  {
        try
        {
            ContactListItems.getInstance().storeContacts();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Exception occured here");
        }
    }

    @Override
    public void init() throws Exception {
        try
        {
            ContactListItems.getInstance().loadContacts();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Exception was found here while loading the items");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}