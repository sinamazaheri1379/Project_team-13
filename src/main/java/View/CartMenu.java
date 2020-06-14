package View;

import Controller.Client.CartController;
import Controller.Client.ClientController;
import Models.Product.Cart;
import Models.Product.Product;
import Models.UserAccount.Seller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class CartMenu extends Menu {
    GridPane productsPages;
    Text totalPriceAmount;


    public CartMenu(Stage stage) {
        super(stage);
        this.stage = stage;
        productsPages = new GridPane();
        setScene();
    }

    public void setScene() {
        setPageGridPain();
        setUpGridPane();
        setMenuBarGridPane();
        setCenterGridPane();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(pageGridPane);
        scene.setRoot(scrollPane);
        scrollPane.setFitToWidth(true);
        bottomGridPane.getRowConstraints().add(new RowConstraints(100, Control.USE_COMPUTED_SIZE, 100, Priority.NEVER, VPos.CENTER, false));
    }

    private void setCenterGridPane() {
        String buttomStyle = "  -fx-background-color: \n" +
                "        #090a0c,\n" +
                "        linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%),\n" +
                "        linear-gradient(#20262b, #191d22),\n" +
                "        radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));\n" +
                "    " +
                "    -fx-background-insets: 0,0,0,0;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );\n" +
                "    -fx-font-family: \"Arial\";\n" +
                "    -fx-text-fill: linear-gradient(white, #d0d0d0);\n" +
                "    -fx-font-size: 12px;\n" +
                "    -fx-padding: 7 10 7 10;" +
                "     -fx-background-radius: 40px;" +
                "    -fx-border-radius: 20px;";
        //  Customer customer=(Customer) ClientController.getInstance().getCurrentUser();
        Text personalInfo = new Text("ali");
        Text pageTitle = new Text("Cart");
        personalInfo.setFont(Font.loadFont("file:src/BalooBhai2-Regular.ttf", 16));
        pageTitle.setStyle("-fx-font-weight: bold;");
        pageTitle.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 28));
        ArrayList<GridPane> gridPanes = new ArrayList<>();
        Seller seller = new Seller("username", "password", "firstname",
                "lastName", "email", "phone number", 123,
                "ali", true);
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(Integer.toString(i), Integer.toString(i * i + i + 1));
        }
        for (int kk = 0; kk < 48; kk++) {
//          Product product = CartController.getInstance().getCurrentCart().getAllproduct().get(kk);
            Product product = new Product("aa", "aaaa" + Integer.toString(kk), "aa" + (char) (kk + 97), seller, 123, "lai", "svdfv", 123, hashMap);
            product.setImagePath("file:E:\\downloads\\photo\\product.png");
            seller.addProduct(product);
            GridPane gridPane = new GridPane();
            ImageView imageView = new ImageView(new Image(product.getImagePath()));
            Text text = new Text("   " + product.getProductName() + "\n" + "   " + product.getCostAfterOff() + " $");
            Label label = new Label("   Total price:");
            Label totalPrice = new Label("4");
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            GridPane scoreGridPane = new GridPane();
            scoreGridPane.setHgap(2);
            scoreGridPane.add(label, 0, 0);
            scoreGridPane.add(totalPrice, 1, 0);
            Label countText = new Label("   Count: ");
            Label count = new Label("4");
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            GridPane countPane = new GridPane();
            countPane.setHgap(2);
            countPane.add(countText, 0, 0);
            countPane.add(count, 1, 0);
            ImageView positive = new ImageView(new Image("file:src/positive.png"));
            ImageView negative = new ImageView(new Image("file:src/negative.png"));
            positive.setFitWidth(25);
            positive.setFitHeight(25);
            negative.setFitWidth(25);
            negative.setFitHeight(25);
            gridPane.add(imageView, 0, 0, 2, 1);
            gridPane.add(text, 0, 1, 1, 1);
            gridPane.add(scoreGridPane, 0, 2, 2, 1);
            gridPane.add(positive, 0, 3);
            GridPane options = new GridPane();
            options.getColumnConstraints().add(new ColumnConstraints(117, Control.USE_COMPUTED_SIZE, 117, Priority.NEVER, HPos.LEFT, false));
//            options.getColumnConstraints().add(new ColumnConstraints(30, Control.USE_COMPUTED_SIZE, 30, Priority.NEVER, HPos.LEFT, false));
//            options.getRowConstraints().add(new RowConstraints(30, Control.USE_COMPUTED_SIZE, 30, Priority.NEVER, VPos.TOP, false));
            options.setHgap(2);
            options.add(countPane, 0, 0);
            options.add(positive, 1, 0);
            options.add(negative, 2, 0);
            gridPane.add(options, 0, 3, 2, 1);
            gridPanes.add(gridPane);
            gridPane.setStyle("-fx-background-color: #ECD5DC;-fx-background-radius: 20px;");
            countText.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 12));
            count.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 12));
            text.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 12));
            label.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 12));
            totalPrice.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 12));
            gridPane.setOnMouseEntered(new EventHandler() {
                @Override
                public void handle(Event event) {
                    scene.setCursor(Cursor.HAND); //Change cursor to hand
                }
            });
            gridPane.setOnMouseExited(new EventHandler() {
                @Override
                public void handle(Event event) {
                    scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
                }
            });
            gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (int i = 0; i < gridPanes.size(); i++) {
                        if (gridPanes.get(i).equals(gridPane)) {
//                            ClientController.getInstance().setCurrentProduct(product);
//                            new ProductMenu(stage).execute();
                        }
                    }
                }
            });
        }
        System.out.println(gridPanes.size());
        ArrayList<GridPane> productsPages = new ArrayList<>();
        for (int j = 0; j < (gridPanes.size() / 12) + (gridPanes.size() % 12 == 0 ? 0 : 1); j++) {
            productsPages.add(new GridPane());
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, 5, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(150, Control.USE_COMPUTED_SIZE, 150, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, 5, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(150, Control.USE_COMPUTED_SIZE, 150, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, 5, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(150, Control.USE_COMPUTED_SIZE, 150, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, 5, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).getColumnConstraints().add(new ColumnConstraints(150, Control.USE_COMPUTED_SIZE, 150, Priority.NEVER, HPos.LEFT, false));
            productsPages.get(j).setVgap(10);
            productsPages.get(j).setHgap(10);
            productsPages.get(j).setMinWidth(600);
            productsPages.get(j).setMaxHeight(300);
            for (int i = j * 12; i < (j) * 12 + (j == ((gridPanes.size() / 12) + (gridPanes.size() % 12 == 0 ? 0 : 1) - 1) ? gridPanes.size() % 12 : 12); i++) {
                productsPages.get(j).add(gridPanes.get(i), 2 * ((i % 12) % 4) + 1, ((i % 12) / 4), 1, 1);
            }
        }
        ArrayList<Button> buttons = new ArrayList<>();
        Button purchase = new Button("Purchase");
        Label totalPrice = new Label("Total Price: ");
//        totalPriceAmount = new Text(Double.toString(CartController.getInstance().getCurrentCart().getTotalPrice()));
        totalPriceAmount = new Text("656566$");
        for (int i = 0; i < productsPages.size(); i++) {
            buttons.add(new Button(Integer.toString(i + 1)));
            buttons.get(i).setStyle(buttomStyle);
            buttons.get(i).setOnMouseEntered(new EventHandler() {
                @Override
                public void handle(Event event) {
                    scene.setCursor(Cursor.HAND); //Change cursor to hand

                }
            });
            buttons.get(i).setOnMouseExited(new EventHandler() {
                @Override
                public void handle(Event event) {
                    scene.setCursor(Cursor.DEFAULT); //Change cursor to hand
                }
            });
            final int[] j = {i};
            buttons.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (Node child : centerGridPane.getChildren()) {
                        if (productsPages.contains(child)) {
                            centerGridPane.getChildren().remove(child);
                            break;
                        }
                    }
                    GridPane buttons1 = new GridPane();
                    buttons1.setHgap(3);
                    for (int i = 0; i < buttons.size(); i++) {
                        buttons1.add(buttons.get(i), i + 1, 0);
                    }
                    GridPane gridPane11 = new GridPane();
                    gridPane11.add(totalPrice, 0, 0);
                    gridPane11.add(totalPriceAmount, 1, 0);
                    gridPane11.add(purchase, 2, 0);
                    gridPane11.setStyle("-fx-background-color: #E6E6E6");
                    buttons1.getColumnConstraints().add(new ColumnConstraints(310 - (buttons.size() / 2) * 20, Control.USE_COMPUTED_SIZE, 310 - (buttons.size() / 2) * 20, Priority.NEVER, HPos.LEFT, false));
                    productsPages.get(j[0]).add(buttons1, 1, 5, 7, 1);
                    productsPages.get(j[0]).add(gridPane11, 1, 6, 7, 2);
                    centerGridPane.add(productsPages.get(j[0]), 1, 1, 2, 2);
                }
            });
        }
        GridPane buttons1 = new GridPane();
        buttons1.setHgap(3);
        for (int i = 0; i < buttons.size(); i++) {
            buttons1.add(buttons.get(i), i + 1, 0);
        }
        buttons1.getColumnConstraints().add(new ColumnConstraints(310 - (buttons.size() / 2) * 20, Control.USE_COMPUTED_SIZE, 310 - (buttons.size() / 2) * 20, Priority.NEVER, HPos.LEFT, false));
        if (productsPages.size() > 0) {
            productsPages.get(0).add(buttons1, 1, 5, 7, 1);
        }
        GridPane leftMenuGridPane = new GridPane();
        leftMenuGridPane.setMinHeight(400);
        leftMenuGridPane.setStyle("-fx-background-color:rgba(45, 156, 240, 1);");
        Button addProduct = new Button("");
        addProduct.setTextAlignment(TextAlignment.CENTER);
        addProduct.setStyle("-fx-font-size: 20 ;-fx-background-color:rgba(45, 156, 240, 0);-fx-text-alignment: center;-fx-text-fill: White;-fx-font-weight: bold;");
        addProduct.setMinHeight(50);
        addProduct.setMinWidth(150);
        leftMenuGridPane.add(addProduct, 0, 2, 2, 2);
        centerGridPane.add(leftMenuGridPane, 0, 1, 1, 6);
        centerGridPane.add(pageTitle, 0, 0, 1, 1);
        if (productsPages.size() > 0) {
            centerGridPane.add(productsPages.get(0), 1, 1, 2, 2);
        } else {
            ImageView imageView = new ImageView(new Image("file:src/empty.png"));
            Text text = new Text("You don't have any product to sell.");
            GridPane gridPane = new GridPane();
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            gridPane.add(imageView, 0, 0);
            gridPane.add(text, 1, 1);
            gridPane.getColumnConstraints().add(new ColumnConstraints(400, Control.USE_COMPUTED_SIZE, 400, Priority.NEVER, HPos.RIGHT, false));
            gridPane.getColumnConstraints().add(new ColumnConstraints(0, Control.USE_COMPUTED_SIZE, 200, Priority.NEVER, HPos.RIGHT, false));
            text.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 32));
            centerGridPane.add(gridPane, 3, 1, 1, 1);
        }
    }

    protected void setUpGridPane() {
        Label label = new Label("        Pms.com");
        label.setStyle("-fx-font-weight: bold;");
        label.setFont(Font.loadFont("file:src/BalooBhai2-Bold.ttf", 28));
        upGridPane.getRowConstraints().add(new RowConstraints(45, Control.USE_COMPUTED_SIZE, 45, Priority.ALWAYS, VPos.CENTER, true));
        upGridPane.add(label, 0, 0);
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
