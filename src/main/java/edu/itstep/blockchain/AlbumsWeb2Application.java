package edu.itstep.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import edu.itstep.blockchain.domain.TransactionPersistent;
import edu.itstep.blockchain.repository.TransactionPersistentRepository;
import edu.itstep.blockchain.wallet.Wallet;

@SpringBootApplication
public class AlbumsWeb2Application {
	Logger logger = LoggerFactory.getLogger(AlbumsWeb2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(AlbumsWeb2Application.class, args);
	}
	@Bean
	public CommandLineRunner run(TransactionPersistentRepository transactionRepo) {
		return(String[] arg)->{
			 Wallet lender =  new Wallet();
			 Wallet georgi = new Wallet();
			TransactionPersistent genesisTransaction = new TransactionPersistent (lender.getPublicKey(),
					georgi.getPublicKey(),10_000, null);
			logger.trace(genesisTransaction + " was added");
			
			
			transactionRepo.save(genesisTransaction);
			
			
		};
	}
}
