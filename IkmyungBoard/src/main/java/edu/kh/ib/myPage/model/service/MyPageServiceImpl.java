package edu.kh.ib.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.ib.board.model.dto.Board;
import edu.kh.ib.board.model.dto.Pagination;
import edu.kh.ib.common.util.Utility;
import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.myPage.model.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper mapper;
	
	private final BCryptPasswordEncoder bcrypt;
	
	@Value("${my.profile.web-path}")
	private String profileWebPath;
	
	@Value("${my.profile.folder-path}")
	private String profileFolderPath;
	
		
	@Override
	public int updateInfo(Member inputMember, String[] memberAddress) {
		
		if( inputMember.getMemberAddress().equals(",,")) {
			
			inputMember.setMemberAddress(null);
		} else {
			String address = String.join("^^^", memberAddress);
			
			inputMember.setMemberAddress(address);
					
		}
		
		
		return mapper.updateInfo(inputMember);
	}
	

	// 비밀번호 변경
	@Override
	public int change(String currentPw, String newPw, Member loginMember) {
		String  change = mapper.change(loginMember);
		
		if(!bcrypt.matches(currentPw , change)) {
			return 0;
		} else 	{
			String encPw = bcrypt.encode(newPw);
			loginMember.setMemberPw(encPw);
			
			return mapper.changePw(loginMember);
		}
		
		
	}
	
	// 계정 탈퇴
	@Override
	public int logout(String memberPw, Member loginMember) {
		String logout = mapper.selectPw(loginMember);
		
		if(!bcrypt.matches(memberPw, logout)) {
			return 0;
		} else {
			
		return mapper.logout(loginMember);
		}
	}
	
	
	
	
	@Override
	public int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException {
		
		// 수정할 경로
		String updatePath = null;
		
		// 변경명 저장
		String rename = null;
		
		
		if( !profileImg.isEmpty() ) {
			
			rename = Utility.fileRename(profileImg.getOriginalFilename());
			
			updatePath = profileWebPath + rename;
			
			
		}
		
		
		Member mem = Member.builder()
						.memberNo(loginMember.getMemberNo())
						.profileImg(updatePath)
						.build();
		
		int result = mapper.profile(mem);
		
		if(result > 0) {
			if( !profileImg.isEmpty()) {
			// 파일을 서버 지정된 폴더에 저장
			profileImg.transferTo(new File(profileFolderPath + rename));
		    }
			
			loginMember.setProfileImg(updatePath);
		}
		return result;
	}
	
	// 내가 쓴 글 조회하기
	@Override
	public Map<String, Object> selectBoardList(int memberNo, int cp) {

		// 삭제되지 않은 게시글 수 조회(회원번호로 조회)
		int listCount = mapper.getListCount(memberNo);
		
		// 게시글 수를 바탕으로 페이지 네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		// 특정 게시판의 지정된 페이지 목록 조회
		
		/* ROWBOUNDS 객체 (Mybatis 제공 객체)
		 * - 지정된 크기(offset)만큼 건너뛰고
		 *   제한된 크기(limit)만큼의 행을 조회하는 객체
		 *   
		 *    --> 페이징 처리가 굉장히 간단해짐!!
		 *  */
		
		int limit = pagination.getLimit();
		
		int offset = (cp - 1) * limit; 
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		/* Mapper 메서드 호출 시
		 * - 첫 번째 매개 변수 -> SQL에 전달할 파라미터
		 * - 두 번째 매개 변수 -> RowBounds 객체 전달 변수
		 *  */
		List<Board> boardList = mapper.selectBoardList(memberNo, rowBounds);
		
		
		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶음
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		
		
		// 5. 결과 반한
		
		
		return map;
		
	}
	
	
	
	
}
	
	
	


