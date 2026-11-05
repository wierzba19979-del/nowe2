package pd12.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pd12.model.CategoryStats;
import pd12.model.Report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReportExporter {
    private final ObjectMapper mapper;
    public ReportExporter() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void exportJson(Report report, String outputPath) throws IOException {
        Files.createDirectories(Path.of(outputPath));
        mapper.writeValue(
                Path.of(outputPath, "report.json").toFile(),
                report
        );
    }

    public void exportSummaryCsv(List<CategoryStats> categoryStats, String outputPath) throws IOException {
        Files.createDirectories(Path.of(outputPath));
        StringBuilder csv = new StringBuilder();
        csv.append("category,revenue,average_price\n");
        for (CategoryStats stats : categoryStats) {
            csv.append(stats.getCategory())
                    .append(",")
                    .append(stats.getRevenue().toPlainString())
                    .append(",")
                    .append(stats.getAveragePrice().toPlainString())
                    .append("\n");
        }
        Files.writeString(
                Path.of(outputPath, "summary.csv"),
                csv.toString()
        );
    }
}