package com.funtravelapp.notif.controller;

import com.funtravelapp.notif.model.notifStatus.NotifStatus;
import com.funtravelapp.notif.service.NotifStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotifStatusController {
    @Autowired
    NotifStatusService service;

    @PostMapping("/create")
    public boolean create(@RequestBody String request){
        try{
            return service.create(request);
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("/all")
    public List<NotifStatus> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public NotifStatus getById(@PathVariable("id") int id){
        return service.getById(id);
    }
}
