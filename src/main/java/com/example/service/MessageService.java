package com.example.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepo;

    @Autowired
    AccountService accountService;

    int status = 200;
    public int getStatus(){
        return status;
    }

    public Message createNewMessage(Message message){
        if (message.getMessageText().length() == 0 || !accountService.checkAccount(message.getPostedBy()) || message.getMessageText().length() >255) {
            status = 400;
            return null;
        }
        status = 200;
        return messageRepo.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepo.findAll();
    }

    public Message getMessageById(int id){
        return messageRepo.findById(id).orElse(null);
    }

    public String deleteMessageById(int id){
        if (getMessageById(id) == null) {
            return "";
        }
        messageRepo.deleteById(id);
        return "1";
    }

    public String updateMessageById(Message message, int id){
        if (message.getMessageText().length() > 255 || message.getMessageText().length() == 0 || !messageRepo.existsById(id)) {
            status = 400;
            return null;
        }
        message.setMessageId(id);
        status = 200;
        return "1";
    }

    public List<Message> getAllMessagesByAccount(int id)
    {
        return messageRepo.getMessagesByAccount(id);
    }




}
