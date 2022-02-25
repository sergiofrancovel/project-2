package com.example.email.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO2 {
    private Integer id;
    private String schedule;
    private String appointmentTime;

    public AppointmentDTO2() {
    }

    public AppointmentDTO2(Integer id, String schedule, String appointmentTime) {
        this.id = id;
        this.schedule = schedule;
        this.appointmentTime = appointmentTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
