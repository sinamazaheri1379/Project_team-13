package View;

import Controller.Client.ClientController;
import Models.UserAccount.Manager;
import com.google.gson.Gson;
import com.sun.rowset.internal.Row;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.regex.Pattern;

public class ManagerMenuScene extends Menu {
    GridPane userInfoGridPane;

    public ManagerMenuScene(Stage stage) {
        super(stage);
        userInfoGridPane = new GridPane();
        userInfoGridPane = new GridPane();
        if (ClientController.getInstance().getMediaPlayer() != null)
            ClientController.getInstance().getMediaPlayer().stop();
        ClientController.getInstance().setMediaPlayer(new MediaPlayer(usersSong));
        ClientController.getInstance().getMediaPlayer().setVolume(0.02);
        ClientController.getInstance().getMediaPlayer().play();
        ClientController.getInstance().getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        setScene();
    }

    public void setScene() {
        setPageGridPain();
        setUpGridPane();
        setMenuBarGridPane();
        setCenterGridPane();
        bottomGridPane.getRowConstraints().add(new RowConstraints(100, Control.USE_COMPUTED_SIZE, 100, Priority.NEVER, VPos.CENTER, true));
    }

    private void setCenterGridPane() {
        Manager manager = (Manager) ClientController.getInstance().getCurrentUser();
        Text personalInfo = new Text(manager.viewPersonalInfo());
        Text pageTitle = new Text("User Menu");
        personalInfo.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 16));
        pageTitle.setStyle("-fx-font-weight: bold;");
        pageTitle.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 28));
        userInfoGridPane.setStyle("-fx-background-color: #ECD5DC;");
        ImageView userIcon;
        if (!manager.getImagePath().equals("")) {
            userIcon = new ImageView(new Image(manager.getImagePath()));
            if (userIcon.getImage().getHeight() == 0) {
                userIcon.setImage(new Image("file:src/user_icon.png"));
            }
        } else {
            userIcon = new ImageView(new Image("file:src/user_icon.png"));
        }
        userIcon.setFitHeight(100);
        userIcon.setFitWidth(100);
        ImageView editInfoPic = new ImageView(new Image("file:src/edit.png"));
        ImageView editPic = new ImageView(new Image("file:src/edit_user_photo.png"));
        editInfoPic.setFitWidth(25);
        editInfoPic.setFitHeight(25);
        editPic.setFitWidth(25);
        editPic.setFitHeight(25);
        Button editInfoButton = new Button("");
        editInfoButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage popupwindow = new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("Edit information.");
                TextField password, firstName, lastName, email, phoneNumber;
                Label firstName1, lastName1, email1, phoneNumber1, password1;
                password = new PasswordField();
                password.setText(manager.getPassword());
                firstName = new TextField(manager.getFirstName());
                lastName = new TextField(manager.getLastName());
                email = new TextField(manager.getEmail());
                phoneNumber = new TextField(manager.getPhoneNumber());
                password.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 120px;");
                firstName.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width:120px;");
                lastName.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 120px;");
                phoneNumber.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 120px;");
                email.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 120px;");
                password.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 14));
                firstName.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 14));
                lastName.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 14));
                phoneNumber.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 14));
                email.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 14));
                password1 = new Label("Password");
                firstName1 = new Label("First Name");
                lastName1 = new Label("Last Name");
                email1 = new Label("Email");
                phoneNumber1 = new Label("Phone Number");
                password1.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                firstName1.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                lastName1.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                phoneNumber1.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                email1.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                Text errors = new Text();
                errors.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 15));
                errors.setFill(Color.RED);
                GridPane gridPane = new GridPane();
                gridPane.add(password, 1, 0);
                gridPane.add(password1, 0, 0);
                gridPane.add(firstName, 1, 1);
                gridPane.add(firstName1, 0, 1);
                gridPane.add(lastName, 1, 2);
                gridPane.add(lastName1, 0, 2);
                gridPane.add(email, 1, 3);
                gridPane.add(email1, 0, 3);
                gridPane.add(phoneNumber, 1, 4);
                gridPane.add(phoneNumber1, 0, 4);
                Button button2 = new Button("Edit");
                button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        errors.setText("");
                        if (checkPasswordIsvalid(password.getText().trim())) {
                            if (checkNameIsvalid(firstName.getText().trim())) {
                                if (checkNameIsvalid(lastName.getText().trim())) {
                                    if (checkEmailIsvalid(email.getText().trim())) {
                                        if (Pattern.matches("\\d+", phoneNumber.getText().trim()) && phoneNumber.getText().trim().length() == 11 && phoneNumber.getText().charAt(0) == '0') {
                                            manager.setFirstName(firstName.getText().trim());
                                            manager.setLastName(lastName.getText().trim());
                                            manager.setEmail(email.getText().trim());
                                            manager.setPhoneNumber(phoneNumber.getText().trim());
                                            manager.setPassword(password.getText().trim());
                                            ClientController.getInstance().sendMessageToServer("@editManager@" + new Gson().toJson(manager));
                                            personalInfo.setText(manager.viewPersonalInfo());
                                            popupwindow.close();
                                        } else {
                                            errors.setText("Phone number is invalid.\nCorrect format:09xxxxxxxxx");
                                        }
                                    } else {
                                        errors.setText("Email format is invalid.\nCorrect Format:ali@ali.com");
                                    }
                                } else {
                                    errors.setText("LastName format is invalid\nuse alphabetical characters");
                                }
                            } else {
                                errors.setText("FirstName format is invalid\nuse alphabetical characters");
                            }
                        } else {
                            errors.setText("Password Format is Invalid.\nuse 9-17 alphabetical character.");
                        }
                    }
                });
                button2.setStyle("-fx-text-fill: white;-fx-background-color:rgba(76, 170, 240, 1);-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 28px;-fx-pref-width: 55px;");
                for (int i = 0; i < 5; i++) {
                    gridPane.getRowConstraints().add(new RowConstraints(20, Control.USE_COMPUTED_SIZE, 20, Priority.NEVER, VPos.CENTER, true));
                }
                gridPane.getRowConstraints().add(new RowConstraints(50, Control.USE_COMPUTED_SIZE, 50, Priority.NEVER, VPos.TOP, true));
                gridPane.getRowConstraints().add(new RowConstraints(20, Control.USE_COMPUTED_SIZE, 20, Priority.NEVER, VPos.CENTER, true));
                gridPane.getColumnConstraints().add(new ColumnConstraints(100, Control.USE_COMPUTED_SIZE, 100, Priority.NEVER, HPos.CENTER, true));
                gridPane.getColumnConstraints().add(new ColumnConstraints(135, Control.USE_COMPUTED_SIZE, 135, Priority.NEVER, HPos.CENTER, true));
                gridPane.add(errors, 0, 5, 2, 2);
                gridPane.add(button2, 0, 6, 2, 2);
                gridPane.setVgap(10);
                gridPane.setHgap(5);
                gridPane.setStyle("-fx-background-color: #ECD5DC;");
                gridPane.setAlignment(Pos.CENTER);
                Scene scene1 = new Scene(gridPane, 350, 300);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();
            }
        });
        editInfoButton.setGraphic(editInfoPic);
        Button editPhotoButton = new Button("");
        FileChooser fileChooser = new FileChooser();
        editPhotoButton.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                userIcon.setImage(new Image("file:" + selectedFile.getAbsolutePath()));
                manager.setImagePath("file:" + selectedFile.getAbsolutePath());
                ClientController.getInstance().sendMessageToServer("@editManager@" + new Gson().toJson(manager));
            }
        });
        editPhotoButton.setGraphic(editPic);
        userInfoGridPane.setVgap(20);
        userInfoGridPane.setHgap(20);
        userInfoGridPane.setMinWidth(600);
        userInfoGridPane.setMaxHeight(300);
        userInfoGridPane.add(personalInfo, 2, 1, 1, 2);
        userInfoGridPane.add(userIcon, 1, 1, 1, 1);
        userInfoGridPane.add(editInfoButton, 9, 2, 1, 1);
        userInfoGridPane.add(editPhotoButton, 10, 2, 1, 1);
        GridPane leftMenuGridPane = new GridPane();
        leftMenuGridPane.setMinHeight(500);
        leftMenuGridPane.setStyle("-fx-background-color:rgba(45, 156, 240, 1);");
        Button requestsButton = new Button("Requests");
        requestsButton.setStyle("-fx-font-size:  16;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        requestsButton.setMinHeight(50);
        requestsButton.setMinWidth(150);
        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        manageUsersButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        manageUsersButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new ManageUsersMenu(stage).execute();
            }
        });

        manageUsersButton.setTextAlignment(TextAlignment.CENTER);
        manageUsersButton.setStyle("-fx-font-size: 14 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        manageUsersButton.setMinHeight(50);
        manageUsersButton.setMinWidth(150);
        Button createManagerButton = new Button("Create Supporter");
        createManagerButton.setTextAlignment(TextAlignment.CENTER);
        createManagerButton.setStyle("-fx-font-size: 14 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        createManagerButton.setMinHeight(50);
        createManagerButton.setMinWidth(150);
        createManagerButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        createManagerButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        createManagerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new CreateManagerProfile(stage).execute();
            }
        });

        Button createSupporterButton = new Button("Create Supoorter");
        createSupporterButton.setTextAlignment(TextAlignment.CENTER);
        createSupporterButton.setStyle("-fx-font-size: 14 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        createSupporterButton.setMinHeight(50);
        createSupporterButton.setMinWidth(150);
        createSupporterButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        createSupporterButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        createSupporterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new CreateSupporterMenu(stage).execute();
            }
        });


        Button wageButton = new Button("Change Wage");
        wageButton.setTextAlignment(TextAlignment.CENTER);
        wageButton.setStyle("-fx-font-size: 14 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        wageButton.setMinHeight(50);
        wageButton.setMinWidth(150);
        wageButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        wageButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        wageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage popupwindow = new Stage();
                GridPane gridPane = new GridPane();
                gridPane.setStyle("-fx-background-color: Blue");
                Button button = new Button("X");
                button.setStyle("-fx-background-color: rgba(236, 213, 220, 0.85);-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 25px; -fx-padding: 3,3,3,3;-fx-font-weight: bold;-fx-text-fill: Red");
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        popupwindow.hide();
                        scene.setFill(null);
                    }
                });
                gridPane.add(button, 0, 0);
                gridPane.add(new Text(""), 1, 0);
                gridPane.setStyle("-fx-background-color: rgba(255,145,200,0.85);");
                GridPane commentPane = new GridPane();
                gridPane.add(commentPane, 1, 1);
                Text titleText = new Text("Wage Percent:");
                TextField getTitle = new TextField();
                getTitle.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 110px;");
                Button addCommentButton = new Button("Change Wage");
                addCommentButton.setStyle("-fx-background-color: #E85D9E;");
                addCommentButton.setMinWidth(100);
                commentPane.setVgap(10);
                commentPane.setHgap(10);
                addCommentButton.setTextFill(Color.WHITE);
                commentPane.add(titleText, 0, 0);
                commentPane.add(getTitle, 1, 0);
                commentPane.add(addCommentButton, 1, 6);
                addCommentButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ClientController.getInstance().sendMessageToServer("@setWage@" + getTitle.getText());
                        popupwindow.hide();
                    }
                });
                Scene scene1 = new Scene(gridPane, 400, 300);
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.initStyle(StageStyle.UNDECORATED);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();
            }
        });
        Button atLeastCreditAmountButton = new Button("At Least Credit");
        Button manageOrders = new Button("Manage Orders");
        manageOrders.setTextAlignment(TextAlignment.CENTER);
        manageOrders.setStyle("-fx-font-size: 12 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        manageOrders.setMinHeight(50);
        manageOrders.setMinWidth(150);
        manageOrders.setOnMouseEntered((EventHandler) event -> {
            System.out.println("11111111");
            scene.setCursor(Cursor.HAND);
        });
        manageOrders.setOnMouseExited((EventHandler) event -> {
            System.out.println("222222222");
            scene.setCursor(Cursor.DEFAULT);
        });
        manageOrders.setOnMouseClicked(event -> {
            System.out.println("3333333333");
            new ManageOrders(stage,0).execute();
        });
        atLeastCreditAmountButton.setTextAlignment(TextAlignment.CENTER);
        atLeastCreditAmountButton.setStyle("-fx-font-size: 12 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        atLeastCreditAmountButton.setMinHeight(50);
        atLeastCreditAmountButton.setMinWidth(150);
        atLeastCreditAmountButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        atLeastCreditAmountButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        atLeastCreditAmountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage popupwindow = new Stage();
                GridPane gridPane = new GridPane();
                gridPane.setStyle("-fx-background-color: Blue");
                Button button = new Button("X");
                button.setStyle("-fx-background-color: rgba(236, 213, 220, 0.85);-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 25px; -fx-padding: 3,3,3,3;-fx-font-weight: bold;-fx-text-fill: Red");
                button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        popupwindow.hide();
                        scene.setFill(null);
                    }
                });
                gridPane.add(button, 0, 0);
                gridPane.add(new Text(""), 1, 0);
                gridPane.setStyle("-fx-background-color: rgba(255,145,200,0.85);");
                GridPane commentPane = new GridPane();
                gridPane.add(commentPane, 1, 1);
                Text titleText = new Text("At Least Amount:");
                TextField getTitle = new TextField();
                getTitle.setStyle("-fx-background-radius: 3,2,2,2;-fx-font-size: 12px;-fx-background-radius: 30; -fx-pref-height: 18px;-fx-pref-width: 110px;");
                Button addCommentButton = new Button("Change At Least Credit");
                addCommentButton.setStyle("-fx-background-color: #E85D9E;");
                addCommentButton.setMinWidth(100);
                commentPane.setVgap(10);
                commentPane.setHgap(10);
                addCommentButton.setTextFill(Color.WHITE);
                commentPane.add(titleText, 0, 0);
                commentPane.add(getTitle, 1, 0);
                commentPane.add(addCommentButton, 1, 6);
                addCommentButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ClientController.getInstance().sendMessageToServer("@setAtLeastCredit@" + getTitle.getText());
                        popupwindow.hide();
                    }
                });
                Scene scene1 = new Scene(gridPane, 400, 300);
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.initStyle(StageStyle.UNDECORATED);
                popupwindow.setScene(scene1);
                popupwindow.showAndWait();
            }
        });



        requestsButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        requestsButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        requestsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new ManageRequestMenu(stage, 0).execute();
            }
        });
        Button manageCategoriesButton = new Button("Manage Categories");
        manageCategoriesButton.setTextAlignment(TextAlignment.CENTER);
        manageCategoriesButton.setStyle("-fx-font-size: 14 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        manageCategoriesButton.setMinHeight(50);
        manageCategoriesButton.setMinWidth(150);
        manageCategoriesButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        manageCategoriesButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        manageCategoriesButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                new ManageCategoryMenu(stage, 0).execute();
            }
        });
        Button manageDiscountsButton = new Button("Manage Discount Codes");
        manageDiscountsButton.setTextAlignment(TextAlignment.CENTER);
        manageDiscountsButton.setStyle("-fx-font-size: 12 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        manageDiscountsButton.setMinHeight(50);
        manageDiscountsButton.setMinWidth(150);
        manageDiscountsButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        manageDiscountsButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        manageDiscountsButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                new ManageDiscountCodesMenu(stage, 0).execute();
            }
        });
        Button manageProductsButton = new Button("Manage Products");
        manageProductsButton.setTextAlignment(TextAlignment.CENTER);
        manageProductsButton.setStyle("-fx-font-size: 16 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        manageProductsButton.setMinHeight(50);
        manageProductsButton.setMinWidth(150);
        manageProductsButton.setOnMouseEntered(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.HAND);

            }
        });
        manageProductsButton.setOnMouseExited(new EventHandler() {
            @Override
            public void handle(Event event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        manageProductsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                new ManageProductsForManager(stage).execute();
            }
        });
        for (int i = 0; i <9 ; i++) {
            leftMenuGridPane.getRowConstraints().add(new RowConstraints(40, Control.USE_COMPUTED_SIZE, 40, Priority.NEVER, VPos.CENTER, true));
        }
        leftMenuGridPane.setHgap(3);
        leftMenuGridPane.add(requestsButton, 0, 0, 2, 1);
        leftMenuGridPane.add(manageUsersButton, 0, 1, 2, 1);
        leftMenuGridPane.add(manageCategoriesButton, 0, 2, 2, 1);
        leftMenuGridPane.add(manageDiscountsButton, 0, 3, 2, 1);
        leftMenuGridPane.add(manageProductsButton, 0, 4, 2, 1);
        leftMenuGridPane.add(createSupporterButton, 0, 5, 2, 1);
        leftMenuGridPane.add(wageButton, 0, 6, 2, 1);
        leftMenuGridPane.add(atLeastCreditAmountButton, 0, 7, 2, 1);
        leftMenuGridPane.add(manageOrders, 0, 8, 2, 1);
        centerGridPane.add(leftMenuGridPane, 0, 1, 1, 6);
        centerGridPane.add(pageTitle, 0, 0, 1, 1);
        centerGridPane.add(userInfoGridPane, 3, 1, 2, 2);
    }

    protected void setPageGridPain() {
        pageGridPane.getRowConstraints().add(new RowConstraints(45, Control.USE_COMPUTED_SIZE, 45, Priority.NEVER, VPos.CENTER, false));
        pageGridPane.getRowConstraints().add(new RowConstraints(40, Control.USE_COMPUTED_SIZE, 40, Priority.ALWAYS, VPos.TOP, true));
        pageGridPane.getRowConstraints().add(new RowConstraints(80, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.TOP, true));
        pageGridPane.getRowConstraints().add(new RowConstraints(100, Control.USE_COMPUTED_SIZE, 100, Priority.NEVER, VPos.BOTTOM, false));
        pageGridPane.getColumnConstraints().add(new ColumnConstraints(0, Control.USE_PREF_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.RIGHT, true));
        pageGridPane.add(upGridPane, 0, 0);
        pageGridPane.add(menuBarGridPane, 0, 1);
        pageGridPane.add(centerGridPane, 0, 2);
        pageGridPane.add(bottomGridPane, 0, 3);
    }

    public void execute() {
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkPasswordIsvalid(String word) {
        if (word.length() > 8 && word.length() < 18) {
            return true;
        }
        return false;
    }

    private boolean checkNameIsvalid(String name) {
        if (Pattern.matches("(([a-z]|[A-Z])+ )*(([a-z]|[A-Z])+)", name) && !name.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean checkEmailIsvalid(String email) {
        if (Pattern.matches("\\w+\\.?\\w*@\\w+\\.\\w+", email)) {
            return true;
        }
        return false;
    }
}
