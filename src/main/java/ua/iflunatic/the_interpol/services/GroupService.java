package ua.iflunatic.the_interpol.services;

import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Group;
import ua.iflunatic.the_interpol.repositories.GroupRepository;

import java.util.List;


@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public void save(Group group) {
        groupRepository.save(group);
    }

    public Group getGroupById(Integer groupId) {
        return groupRepository.findById(groupId).orElse(null);
    }
}
