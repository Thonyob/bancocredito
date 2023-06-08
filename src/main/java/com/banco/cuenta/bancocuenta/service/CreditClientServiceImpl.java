package com.banco.cuenta.bancocuenta.service;

import com.banco.cuenta.bancocuenta.model.document.*;
import com.banco.cuenta.bancocuenta.model.repository.CreditClientRepository;
import com.banco.cuenta.bancocuenta.model.repository.TypeCreditRepository;
import com.banco.cuenta.bancocuenta.model.service.CreditClientService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

@Service
public class CreditClientServiceImpl implements CreditClientService {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private CreditClientRepository creditClientRepository;

    @Autowired
    private TypeCreditRepository typeCreditRepository;

    @Override
    public Maybe<CreditClient> save(CreditClient creditClient) {

        if(this.isValidRequestCredit(creditClient.getCliente().getIdCliente(),creditClient.getCliente().getTipoCliente())){
            return this.creditClientRepository.save(creditClient)
                    .flatMap(creditClientRepository::save)
                    .as(RxJava3Adapter::monoToMaybe);
        }
        return null;

    }

    @Override
    public Flowable<CreditClient> getCreditByIdClient(String idCliente) {
        Query query=new Query();
        query.addCriteria(Criteria.where("cliente.idCliente").in(idCliente));

        return mongoTemplate
                .find(query,CreditClient.class)
                .as(RxJava3Adapter::fluxToFlowable);
    }

    @Override
    public Flux<TypeCredit> getAllTypeCredit() {
        return typeCreditRepository.findAll();
    }

    @Override
    public Flowable<CreditClient> getAllCredit() {
        return creditClientRepository.findAll()
                .as(RxJava3Adapter::fluxToFlowable);
    }

    @Override
    public Maybe<CreditClient> payCredit(String nroCredito, int amount) {

        return this.getCreditByNroCredito(nroCredito)
                .map(cl-> {
                    List<HistorialPagos>  historialList=new ArrayList<>();
                    if(cl.getHistorialPagos()!=null){
                         historialList=cl.getHistorialPagos();
                    }

                    int cuota=1;
                    historialList.add(new HistorialPagos("Pago",historialList.size()==0?cuota:cuota+1,amount,getFechaActual(),cl.getTipoCredito().getIdTipoCredito()));
                    cl.setHistorialPagos(historialList);
                    return cl;
                })
                .to(RxJava3Adapter::maybeToMono)
                .flatMap(creditClientRepository::save)
                .as(RxJava3Adapter::monoToMaybe);
    }

    @Override
    public Maybe<CreditClient> getCreditByNroCredito(String nroCredito) {
        Query query=new Query();
        query.addCriteria(Criteria.where("nroCredito").in(nroCredito));

        return mongoTemplate
                .findOne(query,CreditClient.class)
                .as(RxJava3Adapter::monoToMaybe);
    }

    public boolean isValidRequestCredit(String codigoCliente,String tipoCliente){

        List<CreditClient> listaCreditos = this.getCreditByIdClient(codigoCliente).toList().blockingGet();

        System.out.println(listaCreditos.size());

        Predicate< Boolean> predicate=  f->(
                (tipoCliente.equals("Personal") && listaCreditos.size()==0))||
                (tipoCliente.equals("Empresarial"));

        System.out.println("PREDICATE "+predicate.test(false));

        return predicate.test(false);

    }

    private String getFechaActual(){
        //Fecha
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarr = Calendar.getInstance();
        Date dateObj = calendarr.getTime();
        String formattedDate = dtf.format(dateObj);
        return formattedDate;
    }


}
