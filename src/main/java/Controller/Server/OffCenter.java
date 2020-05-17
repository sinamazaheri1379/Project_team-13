package Controller.Server;

import Models.Offer;
import Models.OfferStatus;
import Models.Product.Product;
import Models.UserAccount.Seller;
import com.google.gson.Gson;
import java.util.ArrayList;

public class OffCenter {
    private ArrayList<Offer> allOffers=new ArrayList<>();
    private static OffCenter offCenter;
    private String lastOffId;

    public OffCenter() {
    }

    public static OffCenter getInstance() {
        if (offCenter == null) {
            offCenter = new OffCenter();
        }
        return offCenter;
    }

    public void createOfferRequest(String message) {
        Gson gson = new Gson();
        Offer offer = gson.fromJson(message, Offer.class);
        offer.setOfferId(getOfferIdForCreateInOffer());
        RequestCenter.getIncstance().addRequest(RequestCenter.getIncstance().makeRequest("AddOffer", message));
        offer.setOfferStatus(OfferStatus.onReviewForCreate);
        ServerController.getInstance().sendMessageToClient(ServerMessageController.getInstance().makeMessage("CreatingOffer", "The Offer Registered For Manager's Confirmation"));
    }

    public String getOfferIdForCreateInOffer() {
        DataBase.getInstance().setLastOfferIdFromDataBase();
        this.lastOffId = "@o" + (Integer.parseInt(lastOffId.substring(2)) + 1);
        DataBase.getInstance().replaceOfferId(lastOffId);
        return this.lastOffId;
    }

    public void setLastOffId(String lastOffId) {
        this.lastOffId = lastOffId;
    }

    public void deleteOff() {

    }

    public void createEditOfferRequest(Offer offer) {
        offer.setOfferStatus(OfferStatus.onReviewForEdit);
        RequestCenter.getIncstance().addRequest(RequestCenter.getIncstance().makeRequest("editOffer", new Gson().toJson(offer)));
        ServerController.getInstance().sendMessageToClient(ServerMessageController.getInstance().makeMessage("editOffer", "The Offer's Edition Is Saved For Manager's Confirmation "));
    }
    public void setAllOffers(ArrayList<Offer> allOffers) {
        this.allOffers = allOffers;
    }
    public Product findProductWithID(String productID) {
        for (Offer offer : allOffers) {
            for (Product product : offer.getProducts()) {
                if (product.getProductId().equals(productID)) {
                    return product;
                }
            }
        }
        return null;
    }

    public ArrayList<Offer> getAllOffers() {
        return allOffers;
    }

    public void removeProduct(String productID) {
        for (Offer offer : allOffers) {
            offer.getProducts().removeIf(product -> product.getProductId().equals(productID));
        }
    }
    public void createNewOff(Offer offer) {
        offer.setOfferId(getOfferIdForCreateInOffer());
        offer.setOfferStatus(OfferStatus.accepted);
        if (allOffers == null) {
            allOffers = new ArrayList<>();
        }
        allOffers.add(offer);
        UserCenter.getIncstance().addOfferToSeller(offer);
        DataBase.getInstance().updateAllOffers(new Gson().toJson(allOffers));
    }

    public void editOffer(Offer newOffer) {
        Offer oldOffer=getOfferByOfferId(newOffer.getOfferId());
        newOffer.setOfferStatus(OfferStatus.accepted);
        allOffers.set(allOffers.indexOf(oldOffer), newOffer);
        UserCenter.getIncstance().editOfferForSeller(newOffer);
        DataBase.getInstance().updateAllOffers(new Gson().toJson(allOffers));
    }

    public void setProductStatusForOffer(Offer offer){

    }

    public Offer getOfferByOfferId(String offerId){
        for (Offer offer : allOffers) {
            if (offer.getOfferId().equals(offerId)) {
                return offer;
            }
        }
        return null;
    }
}
