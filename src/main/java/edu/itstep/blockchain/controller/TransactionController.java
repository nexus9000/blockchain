package edu.itstep.blockchain.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("addTransaction")
	TransactionPersistent add(@RequestBody TransactionPersistent[] transaction) {
		return repo.save(transaction[0]);
	}
	@DeleteMapping("/delete/{id}")
	void deleteTransaction(@PathVariable long id) {
		repo.deleteById(id);
	}
}
