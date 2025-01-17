package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account registerAccount){
        Account acc = accountService.addAccount(registerAccount);
        return ResponseEntity.status(accountService.getStatus()).body(acc);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account loginAccount){
        Account acc = accountService.accountLogin(loginAccount);
        return ResponseEntity.status(accountService.getStatus()).body(acc);
    }

    @PostMapping("/messages")
    public ResponseEntity newMessage(@RequestBody Message message){
        messageService.createNewMessage(message);
        return ResponseEntity.status(messageService.getStatus()).body(message);
    }

    @GetMapping("/messages")
    public ResponseEntity getMessages(){
        List<Message> list = messageService.getAllMessages();
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessageByID(@PathVariable Integer message_id){
        Message message = messageService.getMessageById(message_id);
        return ResponseEntity.status(200).body(message);
    }
    
    @DeleteMapping("messages/{message_id}")
    public ResponseEntity deleteMessages(@PathVariable Integer message_id){
        String linesChanged = messageService.deleteMessageById(message_id);
        return ResponseEntity.status(200).body(linesChanged);
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity patchMessage(@PathVariable Integer message_id, @RequestBody Message message){
        String linesChanged = messageService.updateMessageById(message, message_id);
        return ResponseEntity.status(messageService.getStatus()).body(linesChanged);
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity getMessageByAccount(@PathVariable Integer account_id){
        List<Message> list = messageService.getAllMessagesByAccount(account_id);
        return ResponseEntity.status(200).body(list);
    }
}
