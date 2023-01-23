package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // 어플리케이션이 동작 할 때 딱한번 생성된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객의 요청이 올 때마다 만들어져야한다. 쓰레드간에 공유 x 사용하고 버려야하는 존재.
        // JPA 의 모든 데이터 변경은 트랜잭션안에서 이루어져야 한다.
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 생성
//            Member member = new Member();
//
//            member.setId(2L);
//            member.setName("김유신");

            Member findMember = em.find(Member.class, 1L); // 조회
            findMember.setName("세종대왕"); // 수정
    //            em.remove(findMember); // 삭제
//            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
