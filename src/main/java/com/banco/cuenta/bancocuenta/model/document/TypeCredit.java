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
@Document(collection = "tipo_credito")
public class TypeCredit {

    @Id
    private String id;
    private String idTipoCredito;
    private String nombre;
    private Condiciones condiciones;

}
