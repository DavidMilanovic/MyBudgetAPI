package com.productdock.RBCAccountProject.data;

import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.AccountRequest;
import com.productdock.RBCAccountProject.models.legacy.Account;
import com.productdock.RBCAccountProject.models.legacy.Accounts;
import com.productdock.RBCAccountProject.models.legacy.Transaction;
import com.productdock.RBCAccountProject.repositories.AccountEntityRepository;
import com.productdock.RBCAccountProject.services.AccountService;
import com.productdock.RBCAccountProject.services.TransactionService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LegacyDataLoader {

    @Value("${legacy.data.file-path}")
    private String xmlFilePath;

    private final AccountService accountService;
    private final TransactionService transactionService;

    public LegacyDataLoader(AccountService service, TransactionService transactionService) {
        this.accountService = service;
        this.transactionService = transactionService;
    }

    public void load() throws JAXBException, NotFoundException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Accounts.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        File xmlFile = new File(xmlFilePath);
        Accounts accounts = (Accounts) unmarshaller.unmarshal(xmlFile);

        for (Account account : accounts.getAccounts()) {
            AccountRequest request = new AccountRequest();
            request.setName(account.getName());
            request.setBalance(account.getBalance());
            request.setCurrency(account.getCurrency());
            com.productdock.RBCAccountProject.models.Account inserted = accountService.insert(request);

            if(account.getTransactions() != null){
                for (Transaction transaction : account.getTransactions()) {

                    transactionService.insertLegacyData(transaction, inserted);

                }
            }
        }
    }
}
