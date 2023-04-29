package kz.kbtu.sfcourse.controller.application;

import kz.kbtu.sfcourse.domain.model.account.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import kz.kbtu.sfcourse.service.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(description = "AccountController", name = "Account")
public class ApplicationAccountController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TopicExchange exchange;
    private final AccountService accountService;
    @GetMapping("/application/accounts")
    @Operation(summary = "findAll accounts")
    public List<Account> findAll() {
        List<Account> result = accountService.findAllAccounts();
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.application.get",result);
        return result;
    }
}
