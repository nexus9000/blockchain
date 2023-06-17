package edu.itstep.blockchain.domain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import edu.itstep.blockchain.transactions.TransactionInput;
import edu.itstep.blockchain.transactions.TransactionOutput;
import edu.itstep.blockchain.crypto.CryptographyHelper;
@Data
@Entity
public class TransactionPersistent {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   private String transactionId;
   @Column(length = 1000)
   private PublicKey  sender;
   @Column(length = 1000)
   private PublicKey receiver;
   
   private double amount;
   
   private double fee;
 
   private byte[] signature;
   public TransactionPersistent(PublicKey sender, PublicKey receiver, double amount, List<TransactionInput> inputs) {
	   
	   this.sender = sender;
	   this.receiver = receiver;
	  
	   this.amount = amount;
	   calculateHash();
   }
   private final void calculateHash() {
	   String hashData = sender.toString() +
			   receiver.toString() + Double.toString(amount);
	   this.transactionId = CryptographyHelper.generateHash(hashData);
   }
}
