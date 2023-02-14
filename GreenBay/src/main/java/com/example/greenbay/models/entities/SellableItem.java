package com.example.greenbay.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class SellableItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id", nullable = false)
  private Long itemId;

  private String name;

  private String description;

  private String photoUrl;

  private Double startingPrice;

  private Double lastOfferedBid = 0.00;

  private Double purchasePrice;

  private boolean sold = false;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private AppUser user;

  @OneToMany(mappedBy = "sellableItem", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Bid> bids;

  public SellableItem() {
  }

  public SellableItem(Long itemId, String name, String description, String photoUrl,
      Double startingPrice, Double purchasePrice, AppUser user) {
    this.itemId = itemId;
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.user = user;
  }

  public SellableItem(String name, String description, String photoUrl,
      Double startingPrice, Double purchasePrice, AppUser user) {
    this.name = name;
    this.description = description;
    this.photoUrl = photoUrl;
    this.startingPrice = startingPrice;
    this.purchasePrice = purchasePrice;
    this.user = user;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long item_id) {
    this.itemId = item_id;
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

  public Double getStartingPrice() {
    return startingPrice;
  }

  public void setStartingPrice(Double startingPrice) {
    this.startingPrice = startingPrice;
  }

  public Double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public AppUser getUser() {
    return user;
  }

  public void setUser(AppUser user) {
    this.user = user;
  }

  public boolean isSold() {
    return sold;
  }

  public void setSold(boolean sold) {
    this.sold = sold;
  }

  public Double getLastOfferedBid() {
    return lastOfferedBid;
  }

  public void setLastOfferedBid(Double lastOfferedBid) {
    this.lastOfferedBid = lastOfferedBid;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
