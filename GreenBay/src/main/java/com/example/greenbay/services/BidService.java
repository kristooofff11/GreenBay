package com.example.greenbay.services;

import com.example.greenbay.models.dto.SellableItemDto;
import com.example.greenbay.models.dto.SoldSellableItemDto;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.entities.Bid;
import com.example.greenbay.models.entities.SellableItem;

public interface BidService {

  Bid saveBid(Bid bid, AppUser appUser, SellableItem sellableItem);

  SoldSellableItemDto saveBidAndReturnSoldSellableItemDto(Bid bid,AppUser appUser,SellableItem sellableItem);

  SellableItemDto saveBidAndReturnSellableItemDto(Bid bid,AppUser appUser,SellableItem sellableItem);
}
