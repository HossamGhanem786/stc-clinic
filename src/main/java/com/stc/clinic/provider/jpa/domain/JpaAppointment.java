package com.stc.clinic.provider.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@ToString(callSuper = true)
@Table(name = "stc_appointment")
public class JpaAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false,name = "id")
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", locale = "pt-BR", timezone = "UTC")
    private Date date;

    @Column(name = "canceled")
    private Boolean canceled=false;

    @Column(name = "reason")
    private String reason;

    @Column(name = "patient_name")
    private String patientName;


    }


