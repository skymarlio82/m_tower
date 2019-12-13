package com.mymt;

import com.mymt.bean.ItemsBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.mymt.MTGame.GAME_PIX_72;
import static com.mymt.MTGame.gameFrame;
import static com.mymt.MTGame.gamePanel;
import static com.mymt.MTGame.inConversation;
import static com.mymt.MTGame.interaction;
import static com.mymt.MTGame.playerBean_1;
import static com.mymt.MTGame.timeLabel;
import static com.mymt.util.BattleUtil.battleLPane;
import static com.mymt.util.ForecastUtil.displayForecast;
import static com.mymt.util.ForecastUtil.forecastLPane;
import static com.mymt.util.JumpUtil.displayJump;
import static com.mymt.util.JumpUtil.jumpLPane;
import static com.mymt.util.MsgUtil.msgLPane;
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
        gamePanel.add(forecastLPane);
        gamePanel.add(jumpLPane);
        gamePanel.add(battleLPane);
        gamePanel.add(msgLPane);
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
                                displayJump();
                            }
                            break;
                        // 键盘 L
                        case VK_L:
                            if (ItemsBean.isHasForecast) {
                                displayForecast();
                            }
                            break;
                        default:
                            break;
                    }
                } else if (e.getKeyCode() == e.VK_L) {
                    // bug fixed
                    inConversation = false;
                    forecastLPane.removeAll();
                    forecastLPane.setVisible(false);
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