package com.banco.cuenta.bancocuenta.model.repository;

import com.banco.cuenta.bancocuenta.model.document.TypeCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TypeCreditRepository extends ReactiveMongoRepository<TypeCredit,String> {
}
