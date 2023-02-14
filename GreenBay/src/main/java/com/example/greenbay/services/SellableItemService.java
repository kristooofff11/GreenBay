package com.example.greenbay.services;

import com.example.greenbay.models.dto.SellableItemItemSearchDto;
import com.example.greenbay.models.dto.SoldSellableItemDto;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.entities.SellableItem;
import com.example.greenbay.models.dto.AllSellableItemDto;
import com.example.greenbay.models.dto.SellableItemDto;
import com.example.greenbay.models.dto.SellableItemDtoOutput;

public interface SellableItemService {

  SellableItem saveSellableItem(SellableItemDto itemDto, AppUser appuser);
  SellableItem saveSellableItem(SellableItem sellableItem);

  SellableItem findSellableItemById(Long sellableItemId);

  boolean sellableItemIsExist(Long id);

  SellableItem findSellableItemByName(String sellableItemName);

  AllSellableItemDto convertToAllSellableItemDto(int page);

  SellableItemDtoOutput convertToSellableItemDtoOutput(SellableItem sellableItem);

  SellableItemItemSearchDto convertToSellableItemItemSearchDto(SellableItem sellableItem);
  SellableItemDto convertToSellableItemDto(SellableItem sellableItem);
  SoldSellableItemDto convertToSoldSellableItemDto(SellableItem sellableItem);

}
