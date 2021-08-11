package ua.finalproject.periodicals.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.entity.MoneyAccountException;
import ua.finalproject.periodicals.repository.AccountRepository;

import java.math.BigDecimal;

@Slf4j
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account putMoney(Account account, BigDecimal moneyToPut) {
        if (moneyToPut.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }
        account.setBalance(account.getBalance().add(moneyToPut));
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Account chargeMoney(Account account, BigDecimal moneyToCharge) throws MoneyAccountException {
        if (account.getBalance().compareTo(moneyToCharge) < 0) {
            throw new MoneyAccountException();
        }
        account.setBalance(account.getBalance().subtract(moneyToCharge));
        return accountRepository.save(account);
    }
}
