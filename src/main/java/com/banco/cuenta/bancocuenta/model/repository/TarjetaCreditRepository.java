package com.banco.cuenta.bancocuenta.model.repository;

import com.banco.cuenta.bancocuenta.model.document.TarjetaCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TarjetaCreditRepository extends ReactiveMongoRepository<TarjetaCredit,String> {
}
