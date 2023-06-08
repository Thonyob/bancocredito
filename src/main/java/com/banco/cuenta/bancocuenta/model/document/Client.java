package com.banco.cuenta.bancocuenta.model.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cliente")
public class Client {

    @Id
    private String id;
    private String nDoc;
    private String tDoc;
    private String nombre;
    private typeClient typeClient;
    private String codigoCliente;


}
