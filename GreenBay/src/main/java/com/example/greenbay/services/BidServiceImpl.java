package com.example.greenbay.services;

import com.example.greenbay.models.dto.SellableItemDto;
import com.example.greenbay.models.dto.SoldSellableItemDto;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.entities.Bid;
import com.example.greenbay.models.entities.SellableItem;
import com.example.greenbay.repositories.BidRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService{

  private final BidRepository bidRepository;

  private final AppUserService appUserService;

  private final SellableItemService sellableItemService;

  public BidServiceImpl(BidRepository bidRepository, AppUserService appUserService,
      SellableItemService sellableItemService) {
    this.bidRepository = bidRepository;
    this.appUserService = appUserService;
    this.sellableItemService = sellableItemService;
  }

  @Override
  public Bid saveBid(Bid bid, AppUser appUser, SellableItem sellableItem) {
    bid.setUser(appUser);
    bid.setOwnerOfTheBid(appUser.getUsername());
    bid.setSellableItem(sellableItem);
    bidRepository.save(bid);
    sellableItem.setLastOfferedBid(bid.getBid());
    if (bid.getBid() >= sellableItem.getPurchasePrice()){
      sellableItem.setSold(true);
      appUser.setGreenBayDollars((int) (appUser.getGreenBayDollars() - bid.getBid()));
    }
    appUserService.saveUser(appUser);
    sellableItemService.saveSellableItem(sellableItem);
    return bid;
  }

  @Override
  public SoldSellableItemDto saveBidAndReturnSoldSellableItemDto(Bid bid, AppUser appUser,
      SellableItem sellableItem) {
    saveBid(bid,appUser,sellableItem);
    return sellableItemService.convertToSoldSellableItemDto(sellableItem);
  }

  @Override
  public SellableItemDto saveBidAndReturnSellableItemDto(Bid bid, AppUser appUser,
      SellableItem sellableItem) {
    saveBid(bid,appUser,sellableItem);
    return sellableItemService.convertToSellableItemDto(sellableItem);
  }
}
