package pd15.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(staticName = "of")
@Getter
public class Member {
    private Long id;
    private String name;
    private String email;
    private List<Loan> loans = new ArrayList<>();
    private static final int MAX_ACTIVE_LOANS = 3;

    public boolean canBorrowBook() {
        long activeLoans = loans.stream().filter(loan -> loan.getReturnedAt() == null).count();
        return activeLoans < MAX_ACTIVE_LOANS;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }
}
