package br.com.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByLogin(String login);
}
