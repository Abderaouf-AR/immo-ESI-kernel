package TP.Controlls;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class adminLogin2 {
    @FXML
    private Button AdminLogBtn;

    @FXML
    private Button VisiteurLogBtn;

    @FXML
    private Button QuitBtn;

    @FXML
    void connectAdmin(ActionEvent event) {
        window w = new window();
        w.affichLoginMenu(w.getAgence());
    }

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

    public void connectAdmin(javafx.event.ActionEvent actionEvent) {
        window w = new window();
        w.affichLoginMenu(w.getAgence());
    }

    public void quit(javafx.event.ActionEvent actionEvent) {
        Platform.exit();
    }
}
