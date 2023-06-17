package edu.itstep.blockchain.domain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import edu.itstep.blockchain.transactions.TransactionInput;
import edu.itstep.blockchain.crypto.CryptographyHelper;

@Getter@Setter@NoArgsConstructor
@Entity
public class TransactionPersistent {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column
   private String transactionId;
   @Column(length = 1_000)
   private String  sender;
   @Column(length = 1_000)
   private String receiver;
   
   private double amount;
   
   private double fee;
 
   private byte[] signature;
   
   public TransactionPersistent(String transactionId, String sender, String receiver, double amount,double fee,byte[] signature ) {
	   this.transactionId = transactionId;
	   this.sender = sender;
	   this.receiver = receiver;
	   this.amount = amount;
	   this.fee = fee;
	   this.signature = signature;
   }
   public TransactionPersistent(String sender, String receiver, double amount, List<TransactionInput> inputs) {
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
