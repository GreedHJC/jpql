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

      em.flush();
      em.clear();

      List<MemberDTO> result = em.createQuery("select distinct new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
          .getResultList();

      MemberDTO memberDTO = result.get(0);

      System.out.println("memberDTO = " + memberDTO.getUsername());
      System.out.println("memberDTO = " + memberDTO.getAge());



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