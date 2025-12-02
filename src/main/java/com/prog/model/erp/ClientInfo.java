package com.prog.model.erp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientinfo") // Optional, explicitly defining the table name
public class ClientInfo { // Class name should follow PascalCase naming convention

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientuid;
    private String clientname;
    private String clientemail;
    private String clientnumber;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientuid() {
        return clientuid;
    }

    public void setClientuid(String clientuid) {
        this.clientuid = clientuid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientemail() {
        return clientemail;
    }

    public void setClientemail(String clientemail) {
        this.clientemail = clientemail;
    }

    public String getClientnumber() {
        return clientnumber;
    }

    public void setClientnumber(String clientnumber) {
        this.clientnumber = clientnumber;
    }
}
