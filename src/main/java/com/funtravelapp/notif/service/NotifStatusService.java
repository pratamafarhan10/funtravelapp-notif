package com.funtravelapp.notif.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.funtravelapp.notif.kafka.KafkaTopicConfig;
import com.funtravelapp.notif.kafka.dto.CreateNotifDTO;
import com.funtravelapp.notif.kafka.dto.UpdateNotifStatusDTO;
import com.funtravelapp.notif.model.notifStatus.NotifStatus;
import com.funtravelapp.notif.repository.NotifStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotifStatusService {
    @Autowired
    NotifStatusRepository repository;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    KafkaTopicConfig kafkaTopicConfig;
    @Value("${service.main.basicToken}")
    String mainBasicToken;

    public boolean create(String request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateNotifDTO req = mapper.readValue(request, CreateNotifDTO.class);
        System.out.println("\nData received: " + request);

        Optional<NotifStatus> opt = repository.findByTransactionId(req.getTransactionId());
        if (opt.isPresent()){
            NotifStatus notifStatus = opt.get();
            if (notifStatus.getStatus().equalsIgnoreCase(EmailStatus.SENT.toString())){
                throw new Exception("Email already sent");
            }
            // Resend email
            notifStatus.setStatus("SENT");
            repository.save(notifStatus);
            return true;
        }

        NotifStatus entity = NotifStatus.builder()
                .chainingId(req.getChainingId())
                .customerId(req.getCustomerId())
                .sellerId(req.getSellerId())
                .transactionId(req.getTransactionId())
                .customerEmail("customer@gmail.com")
                .sellerEmail("seller@gmail.com")
                .status(EmailStatus.SENT.toString())
                .build();
        repository.save(entity);

        UpdateNotifStatusDTO updateNotifStatusDTO = UpdateNotifStatusDTO.builder().chainingId(req.getChainingId()).isInvoiceSent("Y").transactionId(req.getTransactionId()).build();

        String msg = mapper.writeValueAsString(updateNotifStatusDTO);
        kafkaTemplate.send(kafkaTopicConfig.updateNotifStatus().name(), msg);
        return true;
    }

    public List<NotifStatus> getAll(){
        return repository.findAll();
    }

    public NotifStatus getById(int id) throws Exception {
        Optional<NotifStatus> opt = repository.findById(id);
        if (opt.isEmpty()){
            throw new Exception("Notification record not found");
        }
        return opt.get();
    }
}
