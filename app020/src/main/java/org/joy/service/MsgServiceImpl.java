package org.joy.service;
//...470p.

import javax.inject.Inject;

import org.joy.dao.IF_MsgDAO;
import org.joy.dao.IF_MsgPointDAO;
import org.joy.domain.MsgVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgServiceImpl implements IF_MsgService {

	@Inject
	private IF_MsgDAO msgDAO;
	
	@Inject
	private IF_MsgPointDAO msgPointDAO;

	/*
	 * ...485p
	 * 	  트랜잭션 (로그처리, 테스트처리, 트랜잭션에 대한 처리, 계좌이체의 출금 및 입금처리).
	 *       데이터베이스 정규화가 잘 돼 있을수록, 트랜잭션 관련을 맺는 데이터는 줄어듦.
	 *       반정규화가 많이 진행되는 경우, 트랜잭션 처리를 하는 상황이 많아짐.
	 *       
	 * ...   트랜잭션의 기본 원칙 :
	 *          원자성 : All or Nothing 으로 한번에 한꺼번에 처리되야 함.
	 *          일관성
	 *          격리성 : 외부의 간섭이 없음.
	 *          영속성 : 트랜잭션 처리 성공후, 그 결과는 계속 보관됨.
	 * ...495p.
	 *    SampleAdvice의 @Before, @Around를 주석처리하고 
	 *    addMessage()에 @Transactional을 선언하면 STS상에 Around형태의 아이콘이
	 *    나타남.
	 * ...테스트를 위해 updatePoint의 sql문에 고의로 오류를 발생시킴. 
	 *    where xxuid = #{uid}
	 * 
	 * ...http://localhost:8080/z3/msg 테스트 결과.
		Content-Type: application/json
		{
		  "receiver_id": "user00",
		  "sender_id": "user02",
		  "message": "안녕!"
		}
	 * 
	 * ...497p.SQL문이 모두 정상인 경우, 하나의 Connection이 열리고,
	 *    두개의 SQL문이 처리된 후 commit()이 됨.
	 *    정상적인 처리의 경우 :
	 *    	INFO : jdbc.connection - 1. Connection opened 로 시작하고
	 *      ......
	 *    	INFO : jdbc.audit - 1. Connection.commit() returned 로 처리됨.
	 *    
	 *    비정상적인 처리의 경우 :
	 *    	404:Bad Request로 정상적인 msgDAO.insert(vo)까지 롤백시켜서 
	 *    	전체트랜잭션이 처리되지 않음.
	 *    	INFO : jdbc.connection - 1. Connection opened 로 시작하고
	 *      ......
	 *    	INFO : jdbc.audit - 1. Connection.rollback() returned 로 처리됨. 
	 * 
	 * ...500p.
	 *    @Transactional 의 우선순위 : 메서드 > 클래스 > 인터페이스.
	 *    
	 * ...488p.
	 *    @Transactional 속성 :
	 *    	전파(Propagation) 속성 :
	 *    		PROPAGATION_MANDATORY, PROPAGATION_NESTED, PROPAGATION_NEVER,
	 *    		PROPAGATION_NOT_SUPPORTED, 
	 *    		PROPAGATION_REQUIRED : 트랜잭션이 있으면 그 상황에서 실행,
	 *    							   없으면 새로운 트랜잭션 실행 기본 설정.
	 *    		PROPAGATION_REQUIRED_NEW, PROPAGTION_SUPPORTS.
	 *    
	 *    	격리(Isolation) 레벨 :
	 *    		DEFAULT : DB 설정, 기본 격리 수준, 기본 설정.
	 *    		SERIALIZABLE : 가장 높은 격리, 성는 저하 우려 있음.
	 *    		READ_UNCOMMITED : 커밋되지 않은 데이터에 대한 읽기 허용.
	 *    		READ_COMMITED : 커밋된 데이터에 대해 읽기 허용.
	 *    		REPEATEABLE_READ : 동일 필드에 대해 다중 접근시 모두 동일한 결과를 보장
	 *    
	 *    	Read-only 속성 :
	 *    		true 인 경우, insert, update, delete 실행시 예외 발생.
	 *    		기본 설정은 false.
	 *    	
	 *    	Rollback-for-예외 :
	 *    		특정 예외 발생시 강제로 Rollback.
	 *    
	 *    	No-rollback-for-예외 :
	 *    		특정 예외의 발생시 Rollback 처리되지 않음.
	 * 
	 */
	@Transactional
	@Override
	public void addMessage(MsgVO vo) throws Exception {
		msgDAO.insert(vo);
		msgPointDAO.update(vo.getSender_id(), 10);
	}

	@Override
	public MsgVO readMessage(String uid, Integer mid) throws Exception {
		
		msgDAO.updateOpenDate(mid);
		msgPointDAO.update(uid, 5);

		return msgDAO.read(mid);
	}

}
