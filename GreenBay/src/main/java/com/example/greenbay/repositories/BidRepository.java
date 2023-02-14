package com.example.greenbay.repositories;

import com.example.greenbay.models.entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

}
