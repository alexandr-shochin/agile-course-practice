package ru.unn.agile.PersonalFinance.ViewModel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PersonalFinance.Model.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.unn.agile.PersonalFinance.ViewModel.AssertHelper.assertContains;

public class WhenExecutingTransaction {
    private AccountViewModel accountViewModel;
    @Before
    public void setUp() throws Exception {
        accountViewModel = new AccountViewModel();
    }

    @Test
    public void andItIsAddedToTransactionsList() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();
        transactionVM.setParentAccount(accountViewModel);

        transactionVM.execute();

        assertContains(accountViewModel.getTransactions(), transactionVM);
    }

    @Test
    public void andAccountBalanceChanges() throws Exception {
        TransactionViewModel transactionVM = new TransactionViewModel();
        transactionVM.setParentAccount(accountViewModel);
        transactionVM.setAmount(10);

        int balanceBeforeTransaction = accountViewModel.getBalance();
        transactionVM.execute();

        assertNotEquals(accountViewModel.getBalance(), balanceBeforeTransaction);
    }

    @Test
    public void andBalanceOfOtherAccountChangesIfTransfer() throws Exception {
        int transferAmount = 1000;
        int startingCashBalance = 10000;
        int startingDebitCardBalance = 50000;
        LedgerViewModel ledgerVM = new LedgerViewModel();
        AccountViewModel cashAccountVM = createAccount(startingCashBalance, "Cash");
        AccountViewModel debitCardAccountVM = createAccount(startingDebitCardBalance, "Debit card");
        ledgerVM.addAccount(cashAccountVM);
        ledgerVM.addAccount(debitCardAccountVM);

        TransactionViewModel newTransaction = new TransactionViewModel();
        newTransaction.setIsTransfer(true);
        newTransaction.setAmount(transferAmount);
        newTransaction.setAccountFrom(cashAccountVM);
        newTransaction.setAccountTo(debitCardAccountVM);
        newTransaction.execute();

        assertEquals(startingDebitCardBalance + transferAmount, debitCardAccountVM.getBalance());
        assertEquals(startingCashBalance - transferAmount, cashAccountVM.getBalance());
    }

    private AccountViewModel createAccount(final int balance, final String name) {
        return new AccountViewModel(new Account(balance, name));
    }
}
