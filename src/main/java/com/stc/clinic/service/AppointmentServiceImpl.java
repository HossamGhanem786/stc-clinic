package com.stc.clinic.service;

import com.stc.clinic.domain.Appointment;
import com.stc.clinic.domain.Response;
import com.stc.clinic.provider.jpa.domain.JpaAppointment;
import com.stc.clinic.provider.jpa.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Appointment> getAllByDate(String stringDate) throws ParseException {

        log.info("Start Time  : " + new Date());
        log.info("Reading Appointments with date: " + stringDate);
        SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateFor.parse(stringDate==null?dateFor.format(new Date()):stringDate);


        List<JpaAppointment> jpaAppointmentList = appointmentRepository.findAppointmentByDate(date);
      List<Appointment> appointmentList=jpaAppointmentList.stream().map
               (jpaAppointment -> modelMapper.map(jpaAppointment,
                       Appointment.class)).collect(Collectors.toList());
        log.info("End Time  : " + new Date());
        return appointmentList;
    }

    @Override
    public List<Appointment> getAllByPatientName(String patientName) {

        log.info("Start Time  : " + new Date());
        log.info("Reading Appointments with patient name: " + patientName);
        List<JpaAppointment> jpaAppointmentList = appointmentRepository.
                findAppointmentByPatientNameContainingIgnoreCase(patientName);
        List<Appointment> appointmentList=jpaAppointmentList.stream().map
                (jpaAppointment -> modelMapper.map(jpaAppointment,
                        Appointment.class)).collect(Collectors.toList());
        log.info("End Time  : " + new Date());
        return appointmentList;
    }

    @Override
    public Response cancelAppointment(Long id, String reason) {
        log.info("Start Time  : " + new Date());
        log.info("Cancel Appointment  with id: " + id);

        Optional<JpaAppointment> jpaAppointmentOptional = appointmentRepository.findById(id);

        if (jpaAppointmentOptional.isPresent()) {
            if (reason == null ||
                    reason.isEmpty()) {
                log.error("Reason Required!");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reason Required!");
            }
            JpaAppointment appointment = jpaAppointmentOptional.get();
            appointment.setReason(reason);
            appointment.setCanceled(true);
            appointmentRepository.save(appointment);
            log.info("End Time  : " + new Date());
        }
        return new Response(" Appointment Canceled Successfully!");
    }

    @Override
    public Response addAppointment(Appointment appointment) {
        log.info("Start Time  : " + new Date());
        log.info("Saving Appointment.. ");

        appointmentRepository.save(modelMapper.map(appointment, JpaAppointment.class));
        log.info("End Time  : " + new Date());
        return new Response(" Appointment Saved Successfully!");
    }
}
