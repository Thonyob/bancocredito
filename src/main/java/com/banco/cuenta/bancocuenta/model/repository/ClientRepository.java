package com.banco.cuenta.bancocuenta.model.repository;

import com.banco.cuenta.bancocuenta.model.document.CreditClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<CreditClient,String> {
}
