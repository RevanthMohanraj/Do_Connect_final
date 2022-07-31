package com.wipro.DoConnectMicroChat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.DoConnectMicroChat.Entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
