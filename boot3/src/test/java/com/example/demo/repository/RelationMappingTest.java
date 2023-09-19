package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.entity.Profile;

@SpringBootTest
@EnableJpaAuditing
public class RelationMappingTest {
	@Autowired
	private BoardRepository5 boardRepo;

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProfileRepository profileRepo;

	@Test
	@DisplayName("게시글 등록 테스트")
	public void testManyToOneInsert() {
		Member user1 = new Member();
		user1.setId("user1");
		user1.setPassword("1111");
		user1.setName("홍길동");
		user1.setRole("member");
//		memberRepo.save(user1);

		Member user2 = new Member();
		user2.setId("user2");
		user2.setPassword("2222");
		user2.setName("이순신");
		user2.setRole("admin");
//		memberRepo.save(user2);

		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(user1);
			board.setTitle("홍길동 제목 " + i);
			board.setContent("홍길동 내용 " + i);
//			boardRepo.save(board);
		}
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(user2);
			board.setTitle("이순신 제목" + i);
			
			board.setContent("이순신 내용" + i);
//			boardRepo.save(board);
		}
		
		memberRepo.save(user1);
		memberRepo.save(user2);

	}

	@Test
	@DisplayName("게시글 상세 조회")
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(1L).get();
		System.out.println("[" + board.getSeq() + "번 게시글 정보]");
		System.out.println("제목:" + board.getTitle());
		System.out.println("내용:" + board.getContent());
		System.out.println("작성자:" + board.getMember().getName());
		System.out.println("작성자 권한:" + board.getMember().getRole());
	}

	@Test
	@DisplayName("특정 멤버의 게시글 목록 조회")
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("user1").get();

		System.out.println("========================");
		System.out.println(member.getName() + "가(이) 저장한 게시글 목록");
		System.out.println("========================");
		List<Board> boardList = member.getBoardList();
		boardList.forEach(board -> System.out.println(board));
	}

	@Test
	@DisplayName("특정 멤버 삭제")
	public void testCascadeDelete() {
		memberRepo.deleteById("user2");
	}

	@Test
	public void testOneToOneInsert() {
		Member member = memberRepo.findById("user1").get();
		Profile profile = new Profile();
		profile.setMember(member);
		profile.setTel("010-123-4567");
		profile.setAddress("서울 강남구 논현동");
		profileRepo.save(profile);
	}

	@Test
	public void testOneToOneSelect() {
		Profile profile = profileRepo.findById(1L).get();
		System.out.println(profile.getTel());
		System.out.println(profile.getAddress());
		System.out.println(profile.getMember().getName());
	}

	@Test
	public void testOneToOneSelect2() {
		Member member = memberRepo.findById("user1").get();
		System.out.println(member.getName());
		System.out.println(member.getRole());
		System.out.println(member.getProfile().getTel());
		System.out.println(member.getProfile().getAddress());
	}
}
