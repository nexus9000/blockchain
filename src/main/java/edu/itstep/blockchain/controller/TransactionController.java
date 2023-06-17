package edu.itstep.blockchain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.itstep.blockchain.domain.TransactionPersistent;
import edu.itstep.blockchain.repository.TransactionPersistentRepository;

@RestController
@RequestMapping("/blockchain/v1")
public class TransactionController {
	@Autowired
	private TransactionPersistentRepository repo;
	
	@GetMapping("/testBlock")
	String testMessage() {
		return "test Message by blockchain api";
	}
	@GetMapping("poolTransactions")
	List<TransactionPersistent> getAll(){
		return (List<TransactionPersistent>) repo.findAll();
	}
	
}
