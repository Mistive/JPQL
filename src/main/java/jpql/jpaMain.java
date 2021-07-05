package jpql;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class jpaMain {

    public static void main(String[] args) {
        //write your code
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setUsername("관리자1");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            String query = "select group_concat(m.username) from Member m";

            List<String> resultList = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : resultList) {
                System.out.println("s = " + s);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        System.out.println("END");
    }

}
