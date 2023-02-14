package com.example.greenbay.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", nullable = false)
  private Long user_id;

  private String username;

  private String password;

  private int greenBayDollars;

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<SellableItem> items = new ArrayList<>();

  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Bid> bids;

  public AppUser() {
  }

  public AppUser(String username, String password) {
    this.username = username;
    this.password = password;
    this.greenBayDollars = 100;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<SellableItem> getItems() {
    return items;
  }

  public void setItems(List<SellableItem> items) {
    this.items = items;
  }

  public int getGreenBayDollars() {
    return greenBayDollars;
  }

  public void setGreenBayDollars(int greenBayDollars) {
    this.greenBayDollars = greenBayDollars;
  }

  public List<Bid> getBids() {
    return bids;
  }

  public void setBids(List<Bid> bids) {
    this.bids = bids;
  }
}
