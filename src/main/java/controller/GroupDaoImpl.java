package controller;

import model.Group;
import model.Student;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

    private static final Logger log = Logger.getLogger(StudentDao.class.getName());
    private EntityManagerFactory factory;

    public GroupDaoImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Group add(Group group) {
        log.info("addGroup");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(group);
            transaction.commit();
            return group;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return group;
    }

    @Override
    public List<Group> getAll() {
        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Group> groups = manager.createQuery("FROM Group", Group.class);
            return groups.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public Group getById(Object id) {
        EntityManager manager = factory.createEntityManager();
        Group toReturn;
        try {
            log.info("get group by id");
            toReturn = manager.find(Group.class, id);
        } finally {
            manager.close();
        }
        return toReturn;
    }

    @Override
    public Group update(Object groupId, Group newData) {
        log.info("updating group");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            Group toUpdate = manager.find(Group.class, groupId);
            transaction.begin();
            toUpdate.setName(newData.getName());
            transaction.commit();
            return toUpdate;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return null;
    }

    @Override
    public Group delete(Object id) {
        log.info("delete group");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Group toDelete = null;
        try {
            toDelete = manager.find(Group.class, id);
            transaction.begin();
            manager.remove(id);
            transaction.commit();
            return toDelete;
        } catch (Exception e) {
            log.error(e);
            transaction.rollback();
        } finally {
            manager.close();
        }
        return toDelete;
    }
}
