package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Movie;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * description
 *
 * @author : jcHwang
 */
public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

    //1차 캐시(영속성 컨텍스트)
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    // 엔티티 매니저는 데이터 변경시 트랙잭션을 시작해야한다.
    tx.begin();   // [트랜잭션] 시작

    try {

      Book book = new Book();
      book.setName("JPA");
      book.setAuthor("김영한");

      em.persist(book);

      em.flush();
      em.clear();

      Book findMovie = em.find(Book.class, book.getId());
      System.out.println("findMovie = " + findMovie);


      tx.commit();  // [트랜잭션] 커밋
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();

  }

}
