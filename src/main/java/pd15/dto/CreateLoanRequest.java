package pd15.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class CreateLoanRequest {
    private Long memberId;
    private Long bookId;
}
