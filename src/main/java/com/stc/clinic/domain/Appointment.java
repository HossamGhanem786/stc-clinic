package com.stc.clinic.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", locale = "pt-BR", timezone = "UTC")
    private Date date;
    private Boolean canceled;
    private String reason;

    private String patientName;

}
