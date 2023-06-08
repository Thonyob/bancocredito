package com.banco.cuenta.bancocuenta.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPagos {

    private String tipo;
    private int cuota;
    private double monto;
    private String fecha;
    private String idTipoCredito;

}
