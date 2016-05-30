package com.gpzuestc.framework.hibernate.service;

import com.gpzuestc.framework.hibernate.dao.PlayerDAO;
import com.gpzuestc.framework.hibernate.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangguopeng on 16/5/26.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    public Long savePlayer(Player player) {
        playerDAO.savePlayer(player);
//        System.out.println();
//        boolean a = true;
//        if (a) {
//            throw new RuntimeException();
//        }
//        player.setName(player.getName() + "rrr");
        return player.getId();
    }

    public void updatePlayer(Player player) {

        Player player1 = playerDAO.getPlayer(10L);
        player1.setName("aaaa");
        playerDAO.update(player1);
    }
}
