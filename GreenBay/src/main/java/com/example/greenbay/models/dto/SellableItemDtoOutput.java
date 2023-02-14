package com.example.greenbay.models.dto;

public class SellableItemDtoOutput {

  private String name;
  private String photoUrl;
  private Double lastOfferedBid;

  public SellableItemDtoOutput() {
  }

  public SellableItemDtoOutput(String name, String photoUrl, Double lastOfferedBid) {
    this.name = name;
    this.photoUrl = photoUrl;
    this.lastOfferedBid = lastOfferedBid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public Double getLastOfferedBid() {
    return lastOfferedBid;
  }

  public void setLastOfferedBid(Double lastOfferedBid) {
    this.lastOfferedBid = lastOfferedBid;
  }
}
