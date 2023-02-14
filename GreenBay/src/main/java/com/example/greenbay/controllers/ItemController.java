package com.example.greenbay.controllers;

import com.example.greenbay.exeptions.MissingParameterException;
import com.example.greenbay.exeptions.NoItemExistException;
import com.example.greenbay.exeptions.NotAcceptableParameterException;
import com.example.greenbay.models.dto.SellableItemDto;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.entities.Bid;
import com.example.greenbay.models.entities.SellableItem;
import com.example.greenbay.services.BidService;
import com.example.greenbay.services.SellableItemService;
import com.example.greenbay.services.TokenService;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

  private final SellableItemService sellableItemService;
  private final TokenService tokenService;

  private final BidService bidService;

  public ItemController(SellableItemService sellableItemService,
      TokenService tokenService, BidService bidService) {
    this.sellableItemService = sellableItemService;
    this.tokenService = tokenService;
    this.bidService = bidService;
  }


  @PostMapping("/create")
  public ResponseEntity<?> createItem(@RequestHeader (name = "Authorization") String bearerToken,
      @RequestBody(required = false) SellableItemDto sellableItemDto){
    if (sellableItemDto == null){
      throw new MissingParameterException("Missing parameters: name, description, photo url, starting price , purchase price","/create");
    }
    if(sellableItemDto.getName() == null || Objects.equals(sellableItemDto.getName(), "")){
      throw new MissingParameterException("Missing parameter: name!", "/create");
    }
    if (sellableItemDto.getDescription() == null || Objects.equals(sellableItemDto.getDescription(),
        "")){
      throw new MissingParameterException("Missing parameter: description!", "/create");
    }
    if (sellableItemDto.getStartingPrice() == null){
      throw new MissingParameterException("Missing parameter: starting price!", "/create");
    }
    if (sellableItemDto.getPurchasePrice() == null){
      throw new MissingParameterException("Missing parameter: purchase price!", "/create");
    }
    if (sellableItemDto.getStartingPrice() <= 0){
      throw new NotAcceptableParameterException("Starting price must be a positive whole number!","/create");
    }
    if (sellableItemDto.getPhotoUrl() == null || Objects.equals(sellableItemDto.getPhotoUrl(), "")){
      throw new MissingParameterException("Missing parameter: photo url!","/create");
    }
    if (sellableItemDto.getPurchasePrice() < sellableItemDto.getStartingPrice()){
      throw new NotAcceptableParameterException("Purchase price must be higher than the starting price!","/create");
    }else {
      return ResponseEntity.status(200).body(sellableItemService.saveSellableItem(sellableItemDto,
          tokenService.getUserFromAuthorizationHeader(bearerToken)));
    }
  }

  @GetMapping("/allItems/{page}")
  public ResponseEntity<?> getNx20SellableItem(@PathVariable (required = false) Integer page){
    if (page < 0){
      throw new NotAcceptableParameterException("Page parameter must be a positive whole number!", "/allItems" );
    }else {
      return ResponseEntity.ok().body(sellableItemService.convertToAllSellableItemDto(page));
    }
  }
  @GetMapping("/allItems")
  public ResponseEntity<?> getFirst20SellableItem(){
      return ResponseEntity.ok().body(sellableItemService.convertToAllSellableItemDto(0));
  }

  @GetMapping("/item/{item_id}")
  public ResponseEntity<?> getSellableItem(
      @PathVariable (name = "item_id") Long item_id){
    if (sellableItemService.findSellableItemById(item_id) == null){
      throw new NoItemExistException("/item", "Item not found!");
    }
    if (sellableItemService.findSellableItemById(item_id).isSold()){
      throw new NoItemExistException("/item", "This item is already sold!");
    }else {
      return ResponseEntity.ok().body(sellableItemService.convertToSellableItemItemSearchDto(sellableItemService.findSellableItemById(item_id)));
    }
  }
  @PostMapping("/bid/{item_id}")
  public ResponseEntity<?> bid(@RequestHeader (name = "Authorization") String bearerToken,@PathVariable (name = "item_id") Long item_id,
      @RequestBody Bid bid){
    AppUser appUser = tokenService.getUserFromAuthorizationHeader(bearerToken);
    SellableItem sellableItem = sellableItemService.findSellableItemById(item_id);
    if (sellableItemService.findSellableItemById(item_id) == null){
      throw new NoItemExistException("/bid", "Item is not exist");
    }
    if (sellableItem.isSold()){
      throw new NotAcceptableParameterException("/bid", "The item cant be bought!");
    }
    if (appUser.getGreenBayDollars() < bid.getBid()){
      throw new NotAcceptableParameterException("/bid", "Not enough money on the user's account");
    }
    if (bid.getBid() <= sellableItem.getLastOfferedBid() || bid.getBid() < sellableItem.getStartingPrice()){
      throw new NotAcceptableParameterException("/bid", "Bid is too low");
    }
    if (bid.getBid() >= sellableItem.getPurchasePrice()){
      return ResponseEntity.ok().body(bidService.saveBidAndReturnSoldSellableItemDto(bid,appUser,sellableItem));
    }else {
      return ResponseEntity.ok().body(bidService.saveBidAndReturnSellableItemDto(bid,appUser,sellableItem));
    }
  }
}
