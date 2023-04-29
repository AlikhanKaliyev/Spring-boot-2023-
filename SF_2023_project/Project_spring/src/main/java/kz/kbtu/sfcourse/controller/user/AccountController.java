package kz.kbtu.sfcourse.controller.user;

import kz.kbtu.sfcourse.domain.dto.AccountCreateDTO;
import kz.kbtu.sfcourse.domain.dto.AccountDTO;
import kz.kbtu.sfcourse.domain.model.account.Account;
import kz.kbtu.sfcourse.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(description = "AccountController", name = "Account")
public class AccountController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TopicExchange exchange;
    private final AccountService accountService;
    @GetMapping("/user/accounts")
    @Operation(summary = "findAll accounts")
    public List<Account> findAll() {
        List<Account> result = accountService.findAllAccounts();
        rabbitTemplate.convertAndSend(exchange.getName(),"routing.user.get",result);
        return result;
    }
    @PostMapping("/user/accounts")
    @Operation(summary = "createAccount")
    public AccountDTO createAccount(@Parameter  @RequestBody AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        AccountDTO result = accountService.createAccount(accountCreateDTO);
//        rabbitTemplate.convertAndSend(exchange.getName(),"routing.user.create",result);
        return result;
    }
}
