package com.kit.dormitory;

import com.kit.dormitory.book.BookService;
import com.kit.dormitory.book.BookServiceImpl;
import com.kit.dormitory.fee.FeePolicy;
import com.kit.dormitory.fee.OldFeePolicy;
import com.kit.dormitory.member.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    MemberService memberService(){
        return new MemberServiceImpl(memberStorage());
    }

    @Bean
    MemberStorage memberStorage()
    {
        return new FileMemberStorage();
    }

    @Bean
    BookService bookService()
    {
        return new BookServiceImpl(feePolicy());
    }

    @Bean
    FeePolicy feePolicy()
    {
        return new OldFeePolicy();
    }
}
