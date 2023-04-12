package com.funtravelapp.notif.service;

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

    public boolean create(NotifStatus request){
        request.setStatus(EmailStatus.SENT.toString());
        repository.save(request);

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
