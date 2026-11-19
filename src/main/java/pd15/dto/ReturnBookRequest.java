package pd15.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ReturnBookRequest {
    private Long loanId;

}
