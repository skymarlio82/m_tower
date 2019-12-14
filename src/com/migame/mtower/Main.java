package com.migame.mtower;

import com.migame.mtower.bean.ItemsBean;
import com.migame.mtower.util.ForecastUtil;
import com.migame.mtower.util.JumpUtil;
import com.migame.mtower.util.MsgUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.migame.mtower.MTGame.GAME_PIX_72;
import static com.migame.mtower.MTGame.gameFrame;
import static com.migame.mtower.MTGame.gamePanel;
import static com.migame.mtower.MTGame.inConversation;
import static com.migame.mtower.MTGame.interaction;
import static com.migame.mtower.MTGame.playerBean_1;
import static com.migame.mtower.MTGame.timeLabel;
import static com.migame.mtower.util.BattleUtil.battleLPane;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

public class Main {

    public static void main(String[] args) {
        gamePanel = new MTGame();
        gamePanel.setPreferredSize(new Dimension(GAME_PIX_72*18, GAME_PIX_72*13));
        gamePanel.add(ForecastUtil.forecastLPane);
        gamePanel.add(JumpUtil.jumpLPane);
        gamePanel.add(battleLPane);
        gamePanel.add(MsgUtil.msgLPane);
        gamePanel.add(timeLabel);
        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!MTGame.inConversation) {
                    switch (e.getKeyCode()) {
                        // 键盘 ↑
                        case VK_UP:
                            if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0) {
                                playerBean_1.setToward(3);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        // 键盘 →
                        case VK_RIGHT:
                            if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0) {
                                playerBean_1.setToward(2);
                                interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        // 键盘 ↓
                        case VK_DOWN:
                            if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0) {
                                playerBean_1.setToward(1);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        // 键盘 ←
                        case VK_LEFT:
                            if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0) {
                                playerBean_1.setToward(0);
                                interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                                gameFrame.repaint();
                            }
                            break;
                        // 键盘 J
                        case VK_J:
                            if (ItemsBean.isHasJump) {
                                JumpUtil.displayJump();
                            }
                            break;
                        // 键盘 L
                        case VK_L:
                            if (ItemsBean.isHasForecast) {
                                ForecastUtil.displayForecast();
                            }
                            break;
                        default:
                            break;
                    }
                } else if (e.getKeyCode() == e.VK_L) {
                    // bug fixed
                    inConversation = false;
                    ForecastUtil.forecastLPane.removeAll();
                    ForecastUtil.forecastLPane.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        gameFrame.setContentPane(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}