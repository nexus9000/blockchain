package edu.itstep.blockchain.wallet;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.itstep.blockchain.algo.Blockchain;
import edu.itstep.blockchain.crypto.CryptographyHelper;
import edu.itstep.blockchain.transactions.Transaction;
import edu.itstep.blockchain.transactions.TransactionInput;
import edu.itstep.blockchain.transactions.TransactionOutput;

public class Wallet {
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public Wallet() {
		KeyPair keyPair = CryptographyHelper.ellipticCurveCrypto();
		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();
	}

	public double calculateBalance() {
		double balance = 0;
		for(Entry<String, TransactionOutput> item : Blockchain.UTXOs.entrySet()) {
			TransactionOutput transactionOutput = item.getValue();
			
			if(transactionOutput.isMine(publicKey)) {
				balance += transactionOutput.getAmount();
			}
		}
		return balance;
	}
	
	public Transaction transferMoney(PublicKey receiver, double money) {
		if(calculateBalance() < money) {
			throw new RuntimeException("insufficient funds!");
		}
		List<TransactionInput> inputs = new ArrayList<>();
		for(Map.Entry<String, TransactionOutput> item : Blockchain.UTXOs.entrySet()) {
			TransactionOutput UTXO = item.getValue();
			if(UTXO.isMine(this.publicKey)) {
				inputs.add(new TransactionInput(UTXO.getId()));
			}
		}//for
		Transaction newTransaction = new Transaction(publicKey,receiver, money, inputs);
		newTransaction.generateSignature(privateKey);
		return newTransaction;
	}
	
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
