package com.stored.poja.endpoint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoredIntEndpoint {

    private final Random random = new Random();

    @GetMapping("/stored-int")
    public String getStoredInt() {
        try {
            File file = new File("stored-int.txt");

            if (file.exists()) {
                String content = Files.readString(file.toPath());
                return "Stored number: " + content;
            } else {
                int randomNumber = random.nextInt(10000);
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(String.valueOf(randomNumber));
                }
                return "New random number: " + randomNumber;
            }
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}
