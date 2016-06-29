package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controllers.ScreensController;

/**
 *
 * @author Adarsh
 */
public class Runnable extends Application {

    public static String screen1ID = "Login";
    public static String screen1File = "/Views/Login.fxml";
    public static String screen2ID = "Admin";
    public static String screen2File = "/Views/AdminScreen.fxml";
    public static String screen3ID = "Inventory Management";
    public static String screen3File = "/Views/InventoryManagementScreen.fxml";
    public static String screen4ID = "UpdateCustomer";
    public static String screen4File = "/Views/UpdateCustomerScreen.fxml";
    public static String screen5ID = "StoreScreen";
    public static String screen5File = "/Views/StoreManagement.fxml";
    public static String screen6ID = "CreateCustomer";
    public static String screen6File = "/Views/CreateCustomerScreen.fxml";
    public static String screen7ID = "InventoryReport";
    public static String screen7File = "/Views/InventoryReportScreen.fxml";
    public static String screen8ID = "OrdersBuilding";
    public static String screen8File = "/Views/OrdersBuildScreen.fxml";

    public static int userid;
    public static String usertType;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainController = new ScreensController();
        mainController.loadScreen(screen1ID, screen1File);
        mainController.loadScreen(screen2ID, screen2File);
        mainController.loadScreen(screen3ID, screen3File);
        mainController.loadScreen(screen4ID, screen4File);
        mainController.loadScreen(screen5ID, screen5File);
        mainController.loadScreen(screen6ID, screen6File);
        mainController.loadScreen(screen7ID, screen7File);
        mainController.loadScreen(screen8ID, screen8File);

        mainController.setScreen(screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainController);

        Scene scene = new Scene(root, 650, 650);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
