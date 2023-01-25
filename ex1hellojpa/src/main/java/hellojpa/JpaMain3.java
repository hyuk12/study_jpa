package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {
    public static void main(String[] args) {
        // 어플리케이션이 동작 할 때 딱한번 생성된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객의 요청이 올 때마다 만들어져야한다. 쓰레드간에 공유 x 사용하고 버려야하는 존재.
        // JPA 의 모든 데이터 변경은 트랜잭션안에서 이루어져야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            Member member = em.find(Member.class, 150L);
            member.setName("Z");

//            if (member.getName().equals("Z")) {
//                em.persist(member);
//            } 이러한 코드가 필요없다.

            System.out.println("==============");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
