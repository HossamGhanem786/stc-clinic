package com.stc.clinic.provider.jpa.repository;

import com.stc.clinic.provider.jpa.domain.JpaAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<JpaAppointment,Long> {
//    @Query("SELECT app from JpaAppointment app where app.date=:date")@Param("date")
    List<JpaAppointment> findAppointmentByDate( Date date);
    List<JpaAppointment> findAppointmentByPatientNameContainingIgnoreCase(String patientName);

}
