package TP.Controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class checkmessagesCon {
    @FXML
    private Button showEstBtn;

    @FXML
    private Button addEstBtn;

    @FXML
    private Button showOwnBtn;

    @FXML
    private Button fltEstBtn;

    @FXML
    private Button chkMsgBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Label est;

    @FXML
    private Label mesg;

    @FXML
    private Label own;


    @FXML
    public void initialize(){
        window w = new window();
        this.est.setText(this.est.getText()+" "+w.getAgence().getNb_notif());
        this.own.setText(this.own.getText()+" "+1);
        this.mesg.setText(this.mesg.getText()+" "+w.getAgence().getNotif(0));
    }

    @FXML
    void showEstate(ActionEvent event) {
        System.out.println(" hello ");
        ((Stage) (showEstBtn.getScene().getWindow())).close();
        window w = new window();
        w.affichShowEst();
        System.out.println();;
    }

    @FXML
    void addEstate(ActionEvent event) {
        System.out.println("hello ");
        //
        window w = new window();
        w.affichAddEst();
    }

    @FXML
    void fltrEstate(ActionEvent event) {
        System.out.println(" hello ");
        ((Stage) (showEstBtn.getScene().getWindow())).close();
        window win = new window();
        win.affichFilterEst();
    }

    @FXML
    void showOwners(ActionEvent event) {
        System.out.println(" hello ");
        ((Stage) (showEstBtn.getScene().getWindow())).close();
        window w = new window();
        w.affichShowOwn();
    }

    @FXML
    void chkMessages(ActionEvent event) {
        System.out.println(" hello ");
        ((Stage) (showEstBtn.getScene().getWindow())).close();
        window w = new window();
        w.affichCheckMessages();
    }

    @FXML
    void logOut(ActionEvent event) {
        System.out.println(" hello ");
        ((Stage) (showEstBtn.getScene().getWindow())).close();
        window w = new window();
        w.affichLoginMenu(w.getAgence());
    }
}
