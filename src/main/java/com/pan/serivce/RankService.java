package com.pan.serivce;

import com.pan.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 榜单
 * @author pan
 * @date 2019/6/20 18:15
 */
@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;


}