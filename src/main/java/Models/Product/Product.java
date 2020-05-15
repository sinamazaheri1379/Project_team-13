package Models.Product;

import Controller.Client.ClientController;
import Models.Comment;
import Models.Offer;
import Models.Score;
import Models.UserAccount.Customer;
import Models.UserAccount.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {

    private String productId;
    private ProductStatus productStatus;
    private String productName;
    private String productCompany;
    private String sellerUsername;
    private ArrayList<Score> allScores;
    private double productCost, costAfterOff;
    private String productsCategory;
    private String description;
    private ArrayList<Comment> commentList;
    private int numberOfAvailableProducts;
    private HashMap<String, String> featuresOfCategoryThatHas;
    private ArrayList<Customer> allBuyers;
    private Offer offer;

    public Product(String productCompany, String productId, String productName, Seller seller, double productCost, String productsCategory, String description, int numberOfAvailableProducts, HashMap<String, String> featuresOfCategoryThatHas) {
        this.productCompany = productCompany;
        this.productId = productId;
        this.productStatus = ProductStatus.inCreatingProgress;
        this.productName = productName;
        this.sellerUsername = seller.getUsername();
        this.productCost = productCost;
        this.productsCategory = productsCategory;
        this.description = description;
        this.numberOfAvailableProducts = numberOfAvailableProducts;
        this.featuresOfCategoryThatHas = featuresOfCategoryThatHas;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void addScore(Score score) {
        allScores.add(score);
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    public double getAverageScore() {
        if (allScores != null) {
            double averageScore = 0;
            for (Score score : allScores) {
                averageScore += score.getRate();
            }
            averageScore /= allScores.size();
            return averageScore;
        }
        return 0.0;
    }

    public double getCostAfterOff() {
        if (offer != null) {
            costAfterOff = this.productCost * ((double) 100 - offer.getAmount()) / (double) 100;
        }
        return this.productCost;
    }

    public String viewProduct() {
        String projectInformation = "";
        projectInformation += this.productName + "\n";
        projectInformation += this.description + "\n";
        projectInformation += this.productCost + "\n";
        projectInformation += this.productId + "\n";
        projectInformation += this.productsCategory + "\n";
        projectInformation += this.sellerUsername + "\n";
        projectInformation += this.getCostAfterOff() + "\n";
        projectInformation += this.getAverageScore() + "\n\n";

        return projectInformation;

    }

    public ArrayList<String> getComments() {
        if (commentList != null) {
            ArrayList<String> allCommentsInStringForm = new ArrayList<>();
            for (Comment comment : commentList) {
                allCommentsInStringForm.add(comment.toString());
            }
            return allCommentsInStringForm;
        }
        return null;
    }

    public String getProductId() {
        return productId;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public String getSeller() {
        return sellerUsername;
    }

    public double getProductCost() {
        return productCost;
    }

    public String getProductsCategory() {
        return productsCategory;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfAvailableProducts() {
        return numberOfAvailableProducts;
    }

    public HashMap<String, String> getFeaturesOfCategoryThatHas() {
        return featuresOfCategoryThatHas;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public void setProductsCategory(String productsCategory) {
        this.productsCategory = productsCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfAvailableProducts(int numberOfAvailableProducts) {
        this.numberOfAvailableProducts = numberOfAvailableProducts;
    }

    public void addFeaturesOfCategoryThatHas(String feature, String featureDetail) {
        if (!this.featuresOfCategoryThatHas.containsKey(feature)) {
            this.featuresOfCategoryThatHas.put(feature, featureDetail);
        }
    }

    public void deleteFeaturesOfCategoryThatHas(String feature) {
        if (!this.featuresOfCategoryThatHas.containsKey(feature)) {
            this.featuresOfCategoryThatHas.remove(feature);
        }
    }

    public void addToAllBuyers(Customer buyer) {
        this.allBuyers.add(buyer);
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
        costAfterOff = ((100 - offer.getAmount()) / 100) * productCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productStatus=" + productStatus +
                ", productName='" + productName + '\'' +
                ", productCompany='" + productCompany + '\'' +
                ", seller=" + sellerUsername +
                ", allScores=" + allScores +
                ", productCost=" + productCost +
                ", costAfterOff=" + costAfterOff +
                ", productsCategory='" + productsCategory + '\'' +
                ", description='" + description + '\'' +
                ", commentList=" + commentList +
                ", numberOfAvailableProducts=" + numberOfAvailableProducts +
                ", featuresOfCategoryThatHas=" + featuresOfCategoryThatHas +
                ", allBuyers=" + allBuyers +
                '}';
    }
    public String productInfoFor(){
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productStatus=" + productStatus +
                ", productName='" + productName + '\'' +
                ", productCompany='" + productCompany + '\'' +
                ", productCost=" + productCost +
                ", costAfterOff=" + costAfterOff +
                ", productsCategory='" + productsCategory + '\'' +
                ", description='" + description + '\'' +
                ", numberOfAvailableProducts=" + numberOfAvailableProducts;
    }
    public void showDigest(){
        String digest="";
        digest+=description+"\n";
        digest+=productCost+"\n";
        digest+=offer.getAmount()+"%\n";
        digest+=productsCategory+"%\n";
        digest+=sellerUsername+"\n";
        digest+=getAverageScore()+"\n";
        ClientController.getInstance().getCurrentMenu().showMessage(digest);
    }
}
