// 여기는 MemberDAO를 구현하고 있는 클래스!

package com.example.spring01.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.MemberDTO;

@Repository	// 이게 있어야 model클래스로 spring에서 관리함
public class MemberDAOImpl implements MemberDAO {

	@Inject
	SqlSession sqlSession;
	//mybatis 세선
	
	@Override
	public List<MemberDTO> list() {
		return sqlSession.selectList("member.list");
		// 매퍼에 있는 파일을 불러옴 member는  namespace, list는 태그의 id (namespace.id)
	}
	@Override
	public void insert(MemberDTO dto) {
			sqlSession.insert("member.insert", dto);
	}
	
	@Override
	public MemberDTO detail(String userid) {
		return sqlSession.selectOne("member.detail", userid);
	}
	@Override
	public void delete(String userid) {
		sqlSession.delete("member.update",userid);
		
	}
	@Override
	public void update(MemberDTO dto) {
		sqlSession.update("member.update",dto);
	}
	@Override
	public boolean check_passwd(String userid, String passwd) {
		boolean result = false;
		// mybatis mapper에 전달할 값이 2개 이상인 경우 
		// dto 또는 맵으로 전달
		Map<String, String> map = new HashMap<>();
		map.put("userid",userid);
		map.put("passwd",passwd);
		int count = sqlSession.selectOne("member.check_passwd", map);
		// SelectOne이 보낼 수 있는 파라미터가 하나뿐이라서 map으로 묶어서 전달함
		// 레코드가 1개면 true, 0개면 false
		if (count==1)
			result = true;
		return result;
	}

}
