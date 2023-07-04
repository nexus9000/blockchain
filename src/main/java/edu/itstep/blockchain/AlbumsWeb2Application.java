package edu.itstep.blockchain;

import java.util.List;

import org.bouncycastle.util.encoders.Base64;
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
		return (String[] arg) -> {
			Wallet lender = new Wallet();
			Wallet georgi = new Wallet();
			List<TransactionPersistent> listTransactions = transactionRepo.findAll();
			if ((listTransactions.size() > 0) && (!transactionRepo.isGenesisExists())) {
				TransactionPersistent genesisTransaction = new TransactionPersistent(Base64.toBase64String(lender.getPublicKey().getEncoded()),
						Base64.toBase64String(georgi.getPublicKey().getEncoded()), 10_000, true);
				logger.trace(genesisTransaction + " was added");
			    transactionRepo.save(genesisTransaction);
			}else if(listTransactions.size() == 0) {
				TransactionPersistent genesisTransaction = new TransactionPersistent(Base64.toBase64String(lender.getPublicKey().getEncoded()),
						Base64.toBase64String(georgi.getPublicKey().getEncoded()), 10_000, true);
				logger.trace( Base64.toBase64String(georgi.getPublicKey().getEncoded())+ " was added");
			    transactionRepo.save(genesisTransaction);
			}

		};
	}
}
