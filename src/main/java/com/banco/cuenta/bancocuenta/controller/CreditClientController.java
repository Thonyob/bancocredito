package com.banco.cuenta.bancocuenta.controller;

import com.banco.cuenta.bancocuenta.model.document.CreditClient;
import com.banco.cuenta.bancocuenta.model.document.TarjetaCredit;
import com.banco.cuenta.bancocuenta.model.document.TypeCredit;
import com.banco.cuenta.bancocuenta.model.service.CreditClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/credit-client")
public class CreditClientController {

    @Autowired
    CreditClientService creditClientService;

    @PostMapping
    public ResponseEntity<Maybe<CreditClient>> save(@RequestBody CreditClient creditClient) {
        return new ResponseEntity<>(creditClientService.save(creditClient), HttpStatus.CREATED);
    }

    @GetMapping("/credit/idCliente/{idCliente}")
    public ResponseEntity<Flowable<CreditClient>> getCreditByIdClient(@PathVariable("idCliente")String idCliente){
        return new ResponseEntity<>(creditClientService.getCreditByIdClient(idCliente),HttpStatus.OK);
        }

    @GetMapping("/credit/typeCredit")
    public ResponseEntity<Flux<TypeCredit>> getAllTypeCredit(){
        return new ResponseEntity<>(creditClientService.getAllTypeCredit(), HttpStatus.OK);
    }

    @GetMapping("/credit/allCredit")
    public ResponseEntity<Flowable<CreditClient>> getAllCredit(){
        return new ResponseEntity<>(creditClientService.getAllCredit(), HttpStatus.OK);
    }

    @GetMapping("/credit/nroCredito/{nroCredito}")
    public ResponseEntity<Maybe<CreditClient>> getCreditByNroCredito(@PathVariable("nroCredito") String nroCredito){
        return new ResponseEntity<>(creditClientService.getCreditByNroCredito(nroCredito), HttpStatus.OK);
    }

    @PostMapping("/credit/payCredit")
    public ResponseEntity<Maybe<CreditClient>> payCredit(@RequestParam ("nroCredito") String nroCredito,
                                                    @RequestParam ("amount") int amount) {
        return new ResponseEntity<>(creditClientService.payCredit(nroCredito,amount), HttpStatus.CREATED);
    }





}
