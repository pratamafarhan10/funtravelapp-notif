package com.funtravelapp.notif.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funtravelapp.notif.model.notifStatus.NotifStatus;
import com.funtravelapp.notif.repository.NotifStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotifStatusService {
    @Autowired
    NotifStatusRepository repository;

    public boolean create(String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        NotifStatus req = mapper.readValue(request, NotifStatus.class);
        req.setStatus(EmailStatus.SENT.toString());
        repository.save(req);

        return true;
    }

    public List<NotifStatus> getAll(){
        return repository.findAll();
    }

    public NotifStatus getById(int id){
        Optional<NotifStatus> opt = repository.findById(id);
        return opt.orElse(null);
    }
}
