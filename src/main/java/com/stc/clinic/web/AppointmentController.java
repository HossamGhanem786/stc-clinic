package com.stc.clinic.web;

import com.stc.clinic.domain.Appointment;
import com.stc.clinic.service.AppointmentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/appointments")
@Api
public class AppointmentController {

    private final AppointmentService appointmentService;
@GetMapping("/by-date")
    public ResponseEntity<?> getAllAppointmentByDate(
        @RequestParam(value = "date",required = false) String localDate) throws ParseException {
       return new ResponseEntity<>( appointmentService.getAllByDate(localDate), HttpStatus.OK);
    }
    @GetMapping("/by-patient-name")
    public ResponseEntity<?> getAllAppointmentForPatient(
            @RequestParam(value = "patientName") String patientName){
        return new ResponseEntity<>( appointmentService.getAllByPatientName(patientName), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addAppointment(
            @RequestBody Appointment appointment){
        return new ResponseEntity<>( appointmentService.addAppointment(appointment), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable("id")Long id,@RequestBody String reason){
        return new ResponseEntity<>( appointmentService.cancelAppointment(id,reason), HttpStatus.OK);
    }

}
