package com.banco.cuenta.bancocuenta.model.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeCreditClient {

    private String idTipoCredito;
    private int monto;

}
