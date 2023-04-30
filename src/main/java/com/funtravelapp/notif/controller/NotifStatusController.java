package com.funtravelapp.notif.controller;

import com.funtravelapp.notif.model.notifStatus.NotifStatus;
import com.funtravelapp.notif.responseMapper.ResponseMapper;
import com.funtravelapp.notif.service.NotifStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotifStatusController {
    @Autowired
    NotifStatusService service;

    @KafkaListener(
            topics = "CreateNotif",
            groupId = "CreateNotif-1"
    )
    public void create(@RequestBody String request) {
        try {
            service.create(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseMapper.ok(null, service.getAll());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        try{
            return ResponseMapper.ok(null, service.getById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }
}
