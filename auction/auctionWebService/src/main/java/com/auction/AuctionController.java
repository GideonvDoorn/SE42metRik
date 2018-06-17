package com.auction;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import auction.service.AuctionMgr;
import auction.service.RegistrationMgr;
import auction.service.SellerMgr;
import com.google.gson.Gson;
import nl.fontys.util.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AuctionController {

    public AuctionMgr auctionMgr;
    public SellerMgr sellerMgr;
    public RegistrationMgr registrationMgr;

    @ResponseBody
    @RequestMapping(path= "findItemByDescription/{description}",method = GET, produces = "application/json")
    public String findItemByDescription(@PathVariable String description){

        auctionMgr = new AuctionMgr();

        System.out.println(auctionMgr.findItemByDescription(description));

        return new Gson().toJson(auctionMgr.findItemByDescription(description));

    }

    @ResponseBody
    @RequestMapping(path= "getItem/{id}",method = GET, produces = "application/json")
    public String getItem(@PathVariable Long id){

        auctionMgr = new AuctionMgr();

        Item i = auctionMgr.getItem(id);
        if(i == null){
            return "No item found with this id";
        }
        else{
            return i.toString();
        }

    }

    @ResponseBody
    @RequestMapping(path= "newBid/{itemId}/{buyerEmail}/{amount}",method = GET, produces = "application/json")
    public String newBid(@PathVariable Long itemId, @PathVariable String buyerEmail, @PathVariable Integer amount){

        auctionMgr = new AuctionMgr();
        registrationMgr = new RegistrationMgr();

        Item bidItem = auctionMgr.getItem(itemId);
        User bidUser = registrationMgr.getUser(buyerEmail);

        if(bidItem == null){
            return "Item does not exist!";
        }
        if(bidUser == null){
            return "oopsie woopsie fucky wucky, buyer does not exist";
        }
        if(amount < 0 ){
            return "Amount should be positive!";
        }


        auctionMgr.newBid(bidItem, bidUser, new Money(amount * 100, "Euro"));




       return "bid for item: " + itemId +" succesful!";


    }

    @ResponseBody
    @RequestMapping(path= "offerItem/{sellerEmail}/{categoryId}/{description}",method = GET, produces = "application/json")
    public String offerItem(@PathVariable String sellerEmail, @PathVariable Integer categoryId, @PathVariable String description){
        sellerMgr = new SellerMgr();
        auctionMgr = new AuctionMgr();
        registrationMgr = new RegistrationMgr();

        User u =registrationMgr.getUser(sellerEmail);
        Category cat = auctionMgr.getCategory(categoryId);
        if(cat == null){
            return "category does not exist!";
        }
        if(u == null){
            return "oopsie woopsie fucky wucky, seller does not exist";
        }



        sellerMgr.offerItem(u,cat , description);

        return "Offer item succesful!";
    }
}
