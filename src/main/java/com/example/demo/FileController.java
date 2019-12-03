package com.example.demo;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@RestController

public class FileController {

    private Logger logger = Logger.getLogger(FileController.class.getName());

    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(String param) throws IOException {

        File file = new File("KENDI DOSYAN");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        logger.info("TIKLANDI");


        return ResponseEntity.ok()
                .header("Set-Cookie", "Yusuf")
                .header("Cache-Control", "private,no-store,no-cache,must-revalidate")
                .header("Pragma", "private")
                .header("content-disposition", "attdachment; filename=abcd.pdf")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
