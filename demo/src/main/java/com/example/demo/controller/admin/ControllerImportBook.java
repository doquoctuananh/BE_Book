package com.example.demo.controller.admin;

import com.example.demo.model.Book;
import com.example.demo.model.ImportBook;
import com.example.demo.model.Type;
import com.example.demo.service.IServiceBook;
import com.example.demo.service.IServiceImportBook;
import com.example.demo.service.Implements.ImplServiceBook;
import com.example.demo.service.Implements.ImplServiceImportBook;
import com.example.demo.service.Implements.ImplServiceType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/dashboard/importbooks")

public class ControllerImportBook {
    @Autowired
    private ImplServiceImportBook serviceImportBook;
    @Autowired
    private ImplServiceBook serviceBook;
    @Autowired
    private ImplServiceType serviceType;

    private final String dirImage = "upload/";

    @GetMapping
    @Transactional
    public ResponseEntity<?> findAll(@RequestParam(name ="page",defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<ImportBook> importBookList = serviceImportBook.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(importBookList);
    }

    @GetMapping("/search")
    @Transactional
    public ResponseEntity<?> search(@RequestParam(value = "Name", defaultValue = "") String name,
                                    @RequestParam(value = "Date", defaultValue = "") LocalDate date,
                                    @RequestParam(value = "page",required = false,defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<ImportBook> search = serviceImportBook.findByNameAndImportDate(name,date,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(search);
    }


    @PostMapping("create")
    @Transactional
    public ResponseEntity<?> createImportBook(
            @RequestParam("bookName") String bookName,
            @RequestParam("description") String description,
            @RequestParam("priceImport") float priceImport,
            @RequestParam("priceExport") float priceExport,
            @RequestParam("quantity") int quantity,
            @RequestParam("author") String author,
            @RequestParam("language") String language,
            @RequestParam(value = "imageUrl") MultipartFile imageUrl ,
            @RequestParam("typeId") int typeId

    ) throws IOException {
        Book findBook = serviceBook.findByBookName(bookName.trim().toUpperCase());
        System.out.println(findBook);
        if (findBook != null) {
            Map<String,String> errors = new HashMap<>();
            errors.put("message", "Sách đã tồn tại không thể nhập kho mới");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
        }

        File dir = new File(dirImage);
        if (!dir.exists()){
            dir.mkdirs();
        }

        String nameImage = UUID.randomUUID() + "_" +imageUrl.getOriginalFilename();
        Path path = Paths.get(dirImage, nameImage);
//        Files.write(path,imageUrl.getBytes());

        Type type = serviceType.findTypeById(typeId);

        Book book = new Book();
        book.setBookName(bookName.trim().toUpperCase());
        book.setAuthor(author.trim());
        book.setDescription(description.trim().toLowerCase());
        book.setPrice(priceExport);
        book.setImageUrl(nameImage);
        book.setLanguage(language);
        book.setQuantity(quantity);
        book.setType(type);
        serviceBook.save(book);
        if(book.getId() >0 || book.getId() != null){
            Files.write(path,imageUrl.getBytes());
        }
        ImportBook importBook = new ImportBook();
        importBook.setName(bookName.trim().toUpperCase());
        importBook.setImportDate(LocalDate.now());
        importBook.setImportPrice(priceImport);
        importBook.setImportQuantity(quantity);
        importBook.setImportBook(book);
        serviceImportBook.save(importBook);
        Map<String,String> create = new HashMap<>();
        create.put("message", "Thêm mới thành công");

        return ResponseEntity.status(HttpStatus.OK).body(create);
    }

}
