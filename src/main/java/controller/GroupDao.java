package controller;

import model.Group;

import java.util.List;

public interface GroupDao {
    Group add(Group group);

    List<Group> getAll();

    Group getById(Object id);

    Group update(Object studentId, Group newData);

    Group delete(Object id);

}
