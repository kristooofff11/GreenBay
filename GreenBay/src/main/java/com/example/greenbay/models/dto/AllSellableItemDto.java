package com.example.greenbay.models.dto;

import java.util.List;

public class AllSellableItemDto {

  private List<SellableItemDtoOutput> allSellableItem;

  public AllSellableItemDto() {
  }

  public AllSellableItemDto(List<SellableItemDtoOutput> allSellableItemDto) {
    this.allSellableItem = allSellableItemDto;
  }

  public List<SellableItemDtoOutput> getAllSellableItem() {
    return allSellableItem;
  }

  public void setAllSellableItem(
      List<SellableItemDtoOutput> allSellableItem) {
    this.allSellableItem = allSellableItem;
  }
}
