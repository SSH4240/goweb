package online.shop.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SpringDataMemberRepositoryTest {
    @Autowired
    private SpringDataMemberRepository springDataMemberRepository;

    @Test
    void 이름으로_검색_테스트(){
        
    }

}