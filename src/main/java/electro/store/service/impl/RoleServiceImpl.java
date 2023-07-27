package electro.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import electro.store.entity.Role;
import electro.store.repository.RoleRepository;
import electro.store.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository dao;

	public List<Role> findAll() {
		return dao.findAll();
	}
}
