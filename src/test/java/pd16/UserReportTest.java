package pd16;

import org.junit.jupiter.api.Test;
import pd16.model.UserReport;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserReportTest {

    @Test
    void shouldCreateReportWithEmptyLists() {
        UserReport report = new UserReport(null, List.of(), List.of(), null, null);
        assertNotNull(report);
        assertTrue(report.todos().isEmpty());
        assertTrue(report.posts().isEmpty());
    }

    @Test
    void shouldNotAllowModifyingReportLists() {
        UserReport report = new UserReport(null, List.of(), List.of(), null, null);
        assertThrows(UnsupportedOperationException.class, () -> report.todos().add(null));
        assertThrows(UnsupportedOperationException.class, () -> report.posts().add(null));
    }
}
