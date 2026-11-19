package pd15.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class MemberDto {
    private Long id;
    private String name;
    private String email;
}
