package kz.kbtu.sfcourse.service;

import kz.kbtu.sfcourse.domain.dto.AccountCreateDTO;
import kz.kbtu.sfcourse.domain.dto.AccountDTO;
import kz.kbtu.sfcourse.domain.event.AccountCreatedEvent;
import kz.kbtu.sfcourse.domain.model.account.Account;
import kz.kbtu.sfcourse.repository.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final ApplicationEventPublisher eventPublisher;

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountDTO createAccount(AccountCreateDTO accountCreateDTO) throws JsonProcessingException {
        Account account = new Account();
        account.setEmail(accountCreateDTO.getEmail());
        account.setPassword(accountCreateDTO.getPassword());
        account.setUsername(accountCreateDTO.getUsername());
        account.setLast_login(accountCreateDTO.getLoginDate());
        Account savedAccount = accountRepository.save(account);

        AccountCreatedEvent event = new AccountCreatedEvent();
        event.setEmittedDate(LocalDateTime.now());
        event.setAggregateObjectType("Account");
        event.setAggregateObjectId(String.valueOf(savedAccount.getId()));

        ObjectMapper mapper = new ObjectMapper();
        event.setMessagePayload(mapper.writeValueAsString(savedAccount));

        eventPublisher.publishEvent(event);

        AccountDTO result = new AccountDTO(
                savedAccount.getUsername(),
                savedAccount.getPassword(),
                savedAccount.getEmail(),
                savedAccount.getLast_login());

        return result;
    }

    //@KafkaListener(topicPattern = "my_super_topic")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
