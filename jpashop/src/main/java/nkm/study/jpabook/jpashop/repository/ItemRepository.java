package nkm.study.jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import nkm.study.jpabook.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId()==null){
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
