package jpql;

import javax.persistence.*;
import java.util.List;

/**
 * description
 *
 * @author : jcHwang
 */
// 10. 객체지향 쿼리 언어
public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setUsername("member1");
      member.setAge(10);
      em.persist(member);

      Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
          .setParameter("username", "member1")
          .getSingleResult();

      System.out.println("singleResult = " + result);





      tx.commit();  // [트랜잭션] 커밋
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
    emf.close();
  }
}