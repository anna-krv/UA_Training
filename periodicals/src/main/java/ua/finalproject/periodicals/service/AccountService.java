package ua.finalproject.periodicals.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.entity.MoneyAccountException;
import ua.finalproject.periodicals.repository.AccountRepository;

@Slf4j
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account putMoney(Account account, double moneyToPut) {
        account.setBalance(account.getBalance() + moneyToPut);
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Account chargeMoney(Account account, double moneyToCharge) throws MoneyAccountException {
        if (account.getBalance() < moneyToCharge) {
            throw new MoneyAccountException();
        }
        return putMoney(account, -moneyToCharge);
    }
}
