package org.tms.finalproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.tms.finalproject.entity.Location;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.entity.order.PaidOrder;
import org.tms.finalproject.repository.OrderRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
// Отключает автоматическое использование встроенной/поддерживаемой БД (Н2), которую в данном случае я не подключал:
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void whenFindByAuthorUserName_thenReturnOrder() {
        // given:

//        Order o1 = new PaidOrder(0, "title1", "description1", null, null, null, new Location(0, "New-York", "Manheton", 23, 87), "ACTIVE_STATUS", 35.0);
//        orderRepository.save(o1);
//        Order o2 = new UnpaidOrder(0, "title2", "description2", null, null, null, new Location(0, "London", "Baker", 23, 87), "CLOSED_STATUS", "WERY");
//        orderRepository.save(o2);
//        Order o3 = new PaidOrder(0, "title3", "description3", null, null, null, new Location(0, "London", "Baker", 23, 87), "CLOSED_STATUS", 12);
//        orderRepository.save(o3);

//        FilterOrderDto filter = new FilterOrderDto("London", "Baker", "PAID_ORDER", 0.0, 0.0);

        User author = new User(0, "Boris", null, null, 0.0, null, null);
        Order o1 = new PaidOrder(
                0, "title1", "description1",
                author,
                null, null,
                new Location(0, "New-York", "Manheton", 23, 87),
                "ACTIVE_STATUS", 35.0);
        entityManager.persist(author);
        entityManager.persist(o1);
        entityManager.flush();

        // when:
        Order found = orderRepository.findAllByAuthor_UserName("Boris").get(0);

        // then
        assertThat(found.getAuthor().getUserName())
                .isEqualTo("kjhjhjk");
    }


}
