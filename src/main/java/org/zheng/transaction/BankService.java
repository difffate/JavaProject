package org.zheng.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create by zxb on 2017/5/6
 */
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    @Transactional
    public void transfer(long fromUserId, long toUserId, double money) {
        //源账号扣款
        this.bankRepository.moneyOut(fromUserId, money);
        int a = 1 / 0;
        //目标账号增加金额
        this.bankRepository.moneyIn(toUserId, money);
    }
}
