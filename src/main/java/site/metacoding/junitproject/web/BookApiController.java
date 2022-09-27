package site.metacoding.junitproject.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.junitproject.service.BookService;
import site.metacoding.junitproject.web.dto.BookSaveReqDto;

@RequiredArgsConstructor
@RestController
public class BookApiController {  // 컴포지션 = has 관계

    private final BookService bookService;

    // 1. 책등록
    // key=value&key=value
    // { "key": value, "key": value }
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody BookSaveReqDto bookSaveReqDto) {
        bookService.책등록하기(bookSaveReqDto);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201 = insert
    }

    // 2. 책목록보기
    public ResponseEntity<?> getBookList() {
        return null;
    }

    // 3. 책한건보기
    public ResponseEntity<?> getBookOne() {
        return null;
    }

    // 4. 책삭제하기
    public ResponseEntity<?> deleteBook() {
        return null;
    }

    // 5. 책수정하기
    public ResponseEntity<?> updateBook() {
        return null;
    }
    
}
