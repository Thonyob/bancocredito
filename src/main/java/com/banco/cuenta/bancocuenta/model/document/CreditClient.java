package com.banco.cuenta.bancocuenta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credito_cliente")
public class CreditClient {

     @Id
     private String id;
     private String nroCredito;
     private ClienteCredit cliente;
     private TypeCreditClient tipoCredito;
     private List<HistorialPagos> historialPagos;


}
