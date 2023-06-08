package com.banco.cuenta.bancocuenta.model.service;

import com.banco.cuenta.bancocuenta.model.document.Client;
import com.banco.cuenta.bancocuenta.model.document.CreditClient;
import com.banco.cuenta.bancocuenta.model.document.TarjetaCredit;
import com.banco.cuenta.bancocuenta.model.document.TypeCredit;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import reactor.core.publisher.Flux;

public interface CreditClientService {

    Maybe<CreditClient> save(CreditClient creditClient);
    Flowable<CreditClient> getCreditByIdClient(String idCliente);
    Flux<TypeCredit> getAllTypeCredit();
    Flowable<CreditClient> getAllCredit();
    Maybe<CreditClient>payCredit(String nroCredito,int amount);
    Maybe<CreditClient>getCreditByNroCredito(String nroCredito);

}
