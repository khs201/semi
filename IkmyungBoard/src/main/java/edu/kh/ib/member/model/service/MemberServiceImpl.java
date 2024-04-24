package edu.kh.ib.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.ib.member.model.dto.Member;
import edu.kh.ib.member.model.mapper.MemberMapper;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	/* BCrypt 암호화 (Spring Security 제공)
	 * - 입력된 문자열(비밀번호)에 salt를 추가한 후 암호화
	 *  -> 암호화 할 때 마다 결과가 다름
	 *  
	 *  ex) 1234 입력     -> $12!asdfg
	 *  ex) 1234 다시 입력 -> $12!qwexs
	 *  
	 *  - 비밀번호 확인 방법
	 *   -> BCryptPasswordEncoder.matches(평문 비밀번호, 암호화된 비밀번호)
	 *     -> 평문 비밀번호와
	 *     	  암호화된 비밀번호가 같은 경우 true
	 *     	  아니면 false 반환
	 *  * 로그인 / 비밀번호 변경 / 탈퇴 등 비밀번호가 입력되는 경우
	 *    DB에 저장된 암호화된 비밀번호를 조회해서
	 *    matches() 메서드로 비교해야 한다!!!
	 *  
	 *  */
		
		@Override
		public int signup(Member inputMember, String[] memberAddress) {
			
			// 주소가 입력되지 않으면
			// inputMember.getMemberAddress() -> ",,"
			// memberAddress -> [,,] 
			
			// 주소가 입력된 경우!!
			if(!inputMember.getMemberAddress().equals(",,")) {
				
				// String.join("구분자", 배열)
				// -> 배열의 모든 요소 사이에 "구분자"를 추가하여
				// 하나의 문자열로 만드는 메서드
				
				// 구분자로 "^^^" 쓴 이유 : 주소, 상세 주소에 없는 임의의 특수문자 작성
				// -> 나중에 꺼내서 3분할 할 때 구분자로 이용할 예정
				String address = String.join("^^^", memberAddress);
				
				// inputMember 주소로 합쳐진 주소를 세팅
				inputMember.setMemberAddress(address);
			} else { // 주소 입력 X
				inputMember.setMemberAddress(null); // null 저장
			}
			
			// 비밀번호를 암호화하여 inputMember에 세팅
			String encPw = bcrypt.encode(inputMember.getMemberPw());
			inputMember.setMemberPw(encPw);
			
			
			// 회원 가입 매퍼 메서드 호출
			// -> Mybatis에 의해서 자동으로 SQL이 수행됨
			// 	  (매퍼 메서드 호출 시 SQL에 사용할 파라미터는 1개만 전달 가능)
			return mapper.signup(inputMember);
		}
		
		// 이메일 중복 검사
		@Override
		public int checkEmail(String memberEmail) {
			
			return mapper.checkEmail(memberEmail);
		}
		
		
		// 닉네임 중복 검사
		@Override
		public int checkNickname(String memberNickname) {
			
			return mapper.checkNickname(memberNickname);
		}
		
		// 전화번호 중복 검사
		@Override
		public int checkTel(String memberTel) {
			
			return mapper.checkTel(memberTel);
		}
		
		// 아이디 중복 검사
		@Override
		public int checkId(String memberId) {
			
			return mapper.checkId(memberId);
		}
		
		
}





































