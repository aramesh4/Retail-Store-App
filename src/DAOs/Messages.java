/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import javafx.scene.control.Alert;

/**
 *
 * @author Adarsh
 */
public class Messages {

    public void alertMessage(Alert.AlertType alerttype, String title, String message) {
        Alert alert = new Alert(alerttype);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
