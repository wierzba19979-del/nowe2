package pd16;

import org.junit.jupiter.api.Test;
import pd16.model.Point;
import pd16.service.PointExtractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PointExtractorTest {

    @Test
    void shouldReturnPointFromValidCoordinates(){
        Point point = new Point(52.333, 24.2424);

        Point result = PointExtractor.extract(point);

        assertEquals(point, result);
    }

    @Test
    void shouldThrowExceptionForInvalidLatitude(){
        Point point = new Point(95.0, 24.2424);

        assertThrows(IllegalArgumentException.class, () -> PointExtractor.extract(point));
    }

    @Test
    void shouldThrowExceptionForInvalidLongnitude(){
        Point point = new Point(52.333, 200.0);

        assertThrows(IllegalArgumentException.class, () -> PointExtractor.extract(point));
    }

    @Test
    void shouldThrowExceptionForNullPoint(){
        assertThrows(IllegalArgumentException.class, () -> PointExtractor.extract(null));
    }
}
