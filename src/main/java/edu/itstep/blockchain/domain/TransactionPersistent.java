package edu.itstep.blockchain.domain;

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
}
