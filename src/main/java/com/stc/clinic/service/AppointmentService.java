package com.stc.clinic.service;

import com.stc.clinic.domain.Appointment;
import com.stc.clinic.domain.Response;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllByDate(String stringDate) throws ParseException;

    List<Appointment> getAllByPatientName(String patientName);
    Response cancelAppointment(Long id, String reason);
    Response addAppointment(Appointment appointment);
}
