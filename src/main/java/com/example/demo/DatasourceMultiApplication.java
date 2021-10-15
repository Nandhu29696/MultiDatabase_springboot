package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.model.book.Book;
import com.example.demo.model.user.User;
import com.example.demo.user.repository.UserRepository;

@SpringBootApplication
@RestController
public class DatasourceMultiApplication {

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private UserRepository userRepo;

	@PostConstruct
	public void addData2DB() {
		userRepo.saveAll(Stream.of(new User(744, "Nandhu"), new User(744, "Vijay")).collect(Collectors.toList()));
		bookRepo.saveAll(Stream.of(new Book(111, "Java"), new Book(112, "Spring boot")).collect(Collectors.toList()));
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepo.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(DatasourceMultiApplication.class, args);
		System.out.println("created");
	}

}
