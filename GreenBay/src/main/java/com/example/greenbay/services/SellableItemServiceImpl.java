package com.example.greenbay.services;

import com.example.greenbay.models.dto.SellableItemItemSearchDto;
import com.example.greenbay.models.dto.SoldSellableItemDto;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.entities.SellableItem;
import com.example.greenbay.models.dto.AllSellableItemDto;
import com.example.greenbay.models.dto.SellableItemDto;
import com.example.greenbay.models.dto.SellableItemDtoOutput;
import com.example.greenbay.repositories.SellableItemRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SellableItemServiceImpl implements SellableItemService{

  private final SellableItemRepository sellableItemRepository;
  @Autowired
  public SellableItemServiceImpl(SellableItemRepository sellableItemRepository) {
    this.sellableItemRepository = sellableItemRepository;
  }

  @Override
  public SellableItem saveSellableItem(SellableItemDto itemDto, AppUser appUser) {
    SellableItem sellableItem = new SellableItem(itemDto.getName(),
        itemDto.getDescription(),
        itemDto.getPhotoUrl(),
        itemDto.getStartingPrice(),
        itemDto.getPurchasePrice(),
        appUser);
    return sellableItemRepository.save(sellableItem);
  }

  @Override
  public SellableItem saveSellableItem(SellableItem sellableItem) {
    return sellableItemRepository.save(sellableItem);
  }

  @Override
  public SellableItem findSellableItemById(Long sellableItemId) {
    return sellableItemRepository.findSellableItemByItemId(sellableItemId);
  }

  @Override
  public boolean sellableItemIsExist(Long id) {
    return sellableItemRepository.findSellableItemByItemId(id) != null;
  }

  @Override
  public SellableItem findSellableItemByName(String sellableItemName) {
    return sellableItemRepository.findSellableItemByName(sellableItemName);
  }

  @Override
  public AllSellableItemDto convertToAllSellableItemDto(int page) {
    List<SellableItem> sellableItems = sellableItemRepository.findSellableItemBySold(false,
        PageRequest.of(page,20));
    List<SellableItemDtoOutput> sellableItemDtoOutputs = new ArrayList<>();
    sellableItems.forEach(sellableItem -> sellableItemDtoOutputs.add(convertToSellableItemDtoOutput(sellableItem)));
    return new AllSellableItemDto(sellableItemDtoOutputs);
  }

  @Override
  public SellableItemDtoOutput convertToSellableItemDtoOutput(SellableItem sellableItem) {
    SellableItemDtoOutput sellableItemDtoOutput = new SellableItemDtoOutput();
    sellableItemDtoOutput.setName(sellableItem.getName());
    sellableItemDtoOutput.setPhotoUrl(sellableItem.getPhotoUrl());
    sellableItemDtoOutput.setLastOfferedBid(sellableItem.getLastOfferedBid());
    return sellableItemDtoOutput;
  }

  @Override
  public SellableItemItemSearchDto convertToSellableItemItemSearchDto(SellableItem sellableItem) {
    SellableItemItemSearchDto sellableItemItemSearchDto = new SellableItemItemSearchDto();
    sellableItemItemSearchDto.setName(sellableItem.getName());
    sellableItemItemSearchDto.setPhotoUrl(sellableItem.getPhotoUrl());
    sellableItemItemSearchDto.setDescription(sellableItem.getDescription());
    sellableItemItemSearchDto.setBids(sellableItem.getBids());
    sellableItemItemSearchDto.setSellerName(sellableItem.getUser().getUsername());
    sellableItemItemSearchDto.setBuyingPrice(sellableItem.getPurchasePrice());
    return sellableItemItemSearchDto;
  }

  @Override
  public SellableItemDto convertToSellableItemDto(SellableItem sellableItem) {
    SellableItemDto sellableItemDto = new SellableItemDto();
    sellableItemDto.setName(sellableItem.getName());
    sellableItemDto.setDescription(sellableItem.getDescription());
    sellableItemDto.setPhotoUrl(sellableItem.getPhotoUrl());
    sellableItemDto.setStartingPrice(sellableItem.getStartingPrice());
    sellableItemDto.setPurchasePrice(sellableItem.getPurchasePrice());
    return sellableItemDto;
  }

  @Override
  public SoldSellableItemDto convertToSoldSellableItemDto(SellableItem sellableItem) {
    SoldSellableItemDto soldSellableItemDto = new SoldSellableItemDto();
    soldSellableItemDto.setName(sellableItem.getName());
    soldSellableItemDto.setDescription(sellableItem.getDescription());
    soldSellableItemDto.setPhotoUrl(sellableItem.getPhotoUrl());
    soldSellableItemDto.setStartingPrice(sellableItem.getStartingPrice());
    soldSellableItemDto.setPurchasePrice(sellableItem.getPurchasePrice());
    AppUser buyer = sellableItem.getBids().get(sellableItem.getBids().size()).getUser();
    soldSellableItemDto.setBuyerName(buyer.getUsername());
    return soldSellableItemDto;
  }
}
