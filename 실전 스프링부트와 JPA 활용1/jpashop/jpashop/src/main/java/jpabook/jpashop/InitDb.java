package jpabook.jpashop;



import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct 사용");
        initService.dbInit1();
        initService.dbInit2();
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1() {
            Member member = createMember();
            em.persist(member);

            Book book1 = createJPABook1();
            em.persist(book1);

            Book book2 = createJPABook2();
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        public void dbInit2() {
            Member member = createMember2();
            em.persist(member);

            Book book1 = createSpring1Book();
            em.persist(book1);

            Book book2 = createSpring2Book();
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);

            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Book createSpring2Book() {
            Book book2 = new Book();
            book2.setName("Spring2 Book");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            return book2;
        }

        private static Book createSpring1Book() {
            Book book1 = new Book();
            book1.setName("Spring1 Book");
            book1.setPrice(20000);
            book1.setStockQuantity(100);
            return book1;
        }

        private static Member createMember2() {
            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("진주", "2", "`2222"));
            return member;
        }

        private static Book createJPABook2() {
            Book book2 = new Book();
            book2.setName("JPA2 Book");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            return book2;
        }

        private static Book createJPABook1() {
            Book book1 = new Book();
            book1.setName("JPA1 Book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            return book1;
        }

        private static Member createMember() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울", "1", "`1111"));
            return member;
        }
    }
}
