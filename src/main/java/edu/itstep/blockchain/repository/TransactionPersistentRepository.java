package edu.itstep.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.itstep.blockchain.domain.TransactionPersistent;
@Repository
public interface TransactionPersistentRepository extends
JpaRepository<TransactionPersistent,Long> {

}
