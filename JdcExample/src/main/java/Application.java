import lombok.extern.slf4j.Slf4j;
import models.Transaction;
import service.TransactionService;

@Slf4j
public class Application {

    public static void main(String[] args) {

        TransactionService transactionService = TransactionService.getInstance();
        Long txnId = transactionService.AddTransaction("2022-04-27 19:34:50","1234","RUPEES","91");
        System.out.println("TransactionId is: "+txnId.toString());

        Transaction transaction = transactionService.GetTransaction(txnId);
        log.debug("id :{} ", transaction.getId());
    }

}
