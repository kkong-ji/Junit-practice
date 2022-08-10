package site.metacoding.junitproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest    // DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired // DI 
    private BookRepository bookRepository;

    @BeforeEach // 각 테스트 시작전에 한번씩 실행
    public void 데이터준비() {
        String title = "junit5";
        String author = "메타코딩";
        Book book = Book.builder()
            .title(title)
            .author(author)
            .build();
        bookRepository.save(book);
    } // 트랜잭션이 어떻게 반영되는 것일까?
    // 가정 1 : [ 데이터준비() + 1. 책등록 ] (T), [ 데이터준비() + 2. 책 목록보기] (T)  --> BookPS의 사이즈가 1이 될 것임 (검증O)
    // 가정 2 : [ 데이터준비() + 1. 책등록 + 데이터준비() + 2. 책 목록보기] (T) ] --> BookPS의 사이즈가 2가 될 것임 (검증X)

    // 결론 : BeforeEach는 항상 다음 메소드 전까지만 Transaction이 같이 돈다.

    // 1. 책 등록
    @Test
    public void 책등록_test() {
        // given (데이터 준비)
        String title = "junit5";
        String author = "메타코딩";
        Book book = Book.builder()
            .title(title)
            .author(author)
            .build();

        // when (테스트 실행)
        Book bookPS = bookRepository.save(book);

        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    } // 트랜잭션 종료 (저장된 데이트를 초기화함)

    // 2. 책 목록보기
    @Test
    public void 책목록보기_test() {
        // given
        String title = "junit5";
        String author = "메타코딩";

        // when
        List<Book> booksPS = bookRepository.findAll();
        
        System.out.println("사이즈 : ==================================== : " + booksPS.size());
        // then
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());
    } // 트랜잭션 종료 (저장된 데이트를 초기화함)

    // 3. 책 한건보기
    @Test
    public void 책한건보기_test() {
        // given
        String title = "junit5";
        String author = "메타코딩";

        // when
        Book bookPS = bookRepository.findById(1L).get();

        // then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }

    // 4. 책 수정

    // 5. 책 삭제
}
