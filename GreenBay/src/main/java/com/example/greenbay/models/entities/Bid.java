package com.example.greenbay.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "itemId")
  @JsonIgnore
  private SellableItem sellableItem;

  @ManyToOne(cascade = {CascadeType.ALL})
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private AppUser user;

  private String ownerOfTheBid;

  private Double bid;

  public Bid() {
  }

  public Bid(Double bid) {
    this.bid = bid;
  }

  public String getOwnerOfTheBid() {
    return ownerOfTheBid;
  }

  public void setOwnerOfTheBid(String ownerOfTheBid) {
    this.ownerOfTheBid = ownerOfTheBid;
  }

  public Double getBid() {
    return bid;
  }

  public void setBid(Double bid) {
    this.bid = bid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SellableItem getSellableItem() {
    return sellableItem;
  }

  public void setSellableItem(SellableItem sellableItem) {
    this.sellableItem = sellableItem;
  }

  public AppUser getUser() {
    return user;
  }

  public void setUser(AppUser user) {
    this.user = user;
  }
}
