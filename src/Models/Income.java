package Models;

import java.time.LocalDate;
import java.util.UUID;

public class Income {
    private String id = UUID.randomUUID().toString();
    public Budget budget;
    public LocalDate date = LocalDate.now();

    public String getId() {
        return id;
    }
}
