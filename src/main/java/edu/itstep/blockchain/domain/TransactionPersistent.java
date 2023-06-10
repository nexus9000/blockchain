package edu.itstep.blockchain.domain;

import java.security.PublicKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TransactionPersistent {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   private String transactionId;
   
   private PublicKey  sender;
   
   private PublicKey receiver;
   
   private double amount;
   
   private double fee;
   
   private byte[] signature;
   
}
