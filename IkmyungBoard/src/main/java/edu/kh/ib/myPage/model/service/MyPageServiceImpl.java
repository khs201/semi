package edu.kh.ib.myPage.model.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	
}
	
	
	


