package com.example.greenbay.models.dto;

import com.example.greenbay.models.entities.Bid;
import java.util.ArrayList;
import java.util.List;

public class SellableItemItemSearchDto {
  private String name;
  private String description;
  private String photoUrl;
  List<Bid> bids = new ArrayList<>();
  private Double buyingPrice;
  private String sellerName;

  public SellableItemItemSearchDto() {
  }

  public SellableItemItemSearchDto(String name, String description, String photoUrl, List<Bid> bids,
      Double buyingPrice, String sellerName) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.bids = bids;
    this.buyingPrice = buyingPrice;
    this.sellerName = sellerName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }

  public Double getBuyingPrice() {
    return buyingPrice;
  }

  public void setBuyingPrice(Double buyingPrice) {
    this.buyingPrice = buyingPrice;
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
}
