package com.example.email;

import com.example.email.dto.EmailAppointmentDTO;
import com.example.email.dto.EmailNoteDTO;
import com.example.email.services.EmailServiceIntImpl;
import com.example.email.services.EmailTemplates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class EmailApplicationTests {

    @MockBean
    Date date;
    @MockBean
    private JavaMailSender javaMailSender;
    @MockBean
    EmailAppointmentDTO appointmentDTO;
    @MockBean
    EmailNoteDTO noteDTO;


    @Test
    void contextLoads() {
    }

    @Test
    void testEmailAppointmentTemplates(){
        EmailTemplates templates = new EmailTemplates();
        templates.setNewAppointment(appointmentDTO);
        templates.getNewAppointment();
    }

    @Test
    void testEmailNoteTemplate(){
        EmailTemplates templates = new EmailTemplates();
        templates.createPatientNote(noteDTO);
        templates.getPatientNote();
    }

    @Test
    void testEmailServiceIntImpl() {
        EmailServiceIntImpl emailServiceInt = new EmailServiceIntImpl(javaMailSender);
        emailServiceInt.sendSimpleMessageForOrder("test", "test", "Test");
    }

    @Test
    void testEmailAppointmentDTO(){
        EmailAppointmentDTO emailAppointmentDTO = new EmailAppointmentDTO("test", "test",
                "test", "test", "test","test", date);
        EmailAppointmentDTO dto = new EmailAppointmentDTO();
        dto.setAppointmentTime(date);
        dto.setDoctorfn("test");
        dto.setDoctorln("test");
        dto.setPatientfn("test");
        dto.setPatientEmail("test");
        dto.setPatientfn("test");
        dto.setPatientln("test");
        dto.setSchedule("test");
        dto.getAppointmentTime();
        dto.getPatientEmail();
        dto.getDoctorfn();
        dto.getDoctorln();
        dto.getPatientfn();
        dto.getPatientln();
        dto.getSchedule();
    }

    @Test
    void testEmailNoteDTO(){
        EmailNoteDTO emailNoteDTO = new EmailNoteDTO(1,"test","test",
        "test", "test");
        EmailNoteDTO dto = new EmailNoteDTO();
        dto.setEmail("test");
        dto.setDoctor("test");
        dto.setNotes("test");
        dto.setId(5);
        dto.setPatient("test");
        dto.getEmail();
        dto.getDoctor();
        dto.getPatient();
        dto.getId();
        dto.getNotes();
    }

}
