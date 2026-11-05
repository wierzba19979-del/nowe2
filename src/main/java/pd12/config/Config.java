package pd12.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Config {
    private String inputPath;
    private String outputPath;
    private String dateFormat;
}