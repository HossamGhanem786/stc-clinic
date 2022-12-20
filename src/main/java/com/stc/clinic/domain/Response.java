package com.stc.clinic.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;


}
