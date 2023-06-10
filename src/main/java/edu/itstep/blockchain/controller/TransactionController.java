package edu.itstep.blockchain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blockchain/v1")
public class TransactionController {
	@GetMapping("/testBlock")
	String testMessage() {
		return "test Message by blockchain api";
	}
}
