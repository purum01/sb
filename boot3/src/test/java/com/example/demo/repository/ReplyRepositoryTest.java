package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.ReplyEntity;

@SpringBootTest
@EnableJpaAuditing
public class ReplyRepositoryTest {

	@Autowired
	private ReplyRepository replyrepo;

	@Test
	public void saveTest() {
		ReplyEntity reply = new ReplyEntity();
		reply.setReplyer("홍길동");
		reply.setReplyText("답글 테스트입니다");
		replyrepo.save(reply);
	}
}
