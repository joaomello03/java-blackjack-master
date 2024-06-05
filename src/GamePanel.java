//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Cards.PlayerCardHand;
import Players.Dealer;
import Players.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
    private Dealer dealer;
    private Player player;
    private GameTable table;
    private JButton newGameButton = new JButton("Deal");
    private JButton hitButton = new JButton("Hit");
    private JButton doubleButton = new JButton("Double");
    private JButton standButton = new JButton("Stand");
    private JButton add1Chip = new JButton("1");
    private JButton add5Chip = new JButton("5");
    private JButton add10Chip = new JButton("10");
    private JButton add25Chip = new JButton("25");
    private JButton add100Chip = new JButton("100");
    private JButton clearBet = new JButton("Clear");
    private JLabel currentBet = new JLabel("Please set your bet...");
    private JLabel playerWallet = new JLabel("$999.99");
    private JLabel cardsLeft = new JLabel("Cards left...");
    private JLabel dealerSays = new JLabel("Dealer says...");

    public GamePanel() {
        this.setLayout(new BorderLayout());
        this.table = new GameTable();
        this.add(this.table, "Center");
        JPanel var1 = new JPanel();
        var1.add(this.currentBet);
        var1.add(this.clearBet);
        var1.add(this.add1Chip);
        var1.add(this.add5Chip);
        var1.add(this.add10Chip);
        var1.add(this.add25Chip);
        var1.add(this.add100Chip);
        var1.add(this.playerWallet);
        JPanel var2 = new JPanel();
        var2.add(this.dealerSays);
        JPanel var3 = new JPanel();
        var3.add(this.newGameButton);
        var3.add(this.hitButton);
        var3.add(this.doubleButton);
        var3.add(this.standButton);
        var3.add(this.cardsLeft);
        JPanel var4 = new JPanel();
        var4.setLayout(new GridLayout(0, 1));
        var4.add(var2);
        var4.add(var1);
        var4.add(var3);
        this.add(var4, "South");
        var1.setOpaque(false);
        var2.setOpaque(false);
        var3.setOpaque(false);
        var4.setOpaque(false);
        this.newGameButton.addActionListener(this);
        this.hitButton.addActionListener(this);
        this.doubleButton.addActionListener(this);
        this.standButton.addActionListener(this);
        this.clearBet.addActionListener(this);
        this.add1Chip.addActionListener(this);
        this.add5Chip.addActionListener(this);
        this.add10Chip.addActionListener(this);
        this.add25Chip.addActionListener(this);
        this.add100Chip.addActionListener(this);
        this.newGameButton.setToolTipText("Deal a new game.");
        this.hitButton.setToolTipText("Request another card.");
        this.doubleButton.setToolTipText("Double your bet, and receive another card.");
        this.standButton.setToolTipText("Stand with your card-hand.");
        this.clearBet.setToolTipText("Clear your current bet.");
        this.add1Chip.setToolTipText("Add a $1 chip to your current bet.");
        this.add5Chip.setToolTipText("Add a $5 chip to your current bet.");
        this.add10Chip.setToolTipText("Add a $10 chip to your current bet.");
        this.add25Chip.setToolTipText("Add a $25 chip to your current bet.");
        this.add100Chip.setToolTipText("Add a $100 chip to your current bet.");
        this.customizeButton(this.newGameButton, Color.GREEN, Color.WHITE);
        this.customizeButton(this.hitButton, Color.BLUE, Color.WHITE);
        this.customizeButton(this.doubleButton, Color.ORANGE, Color.WHITE);
        this.customizeButton(this.standButton, Color.RED, Color.WHITE);
        this.customizeButton(this.clearBet, Color.GRAY, Color.WHITE);
        this.customizeButton(this.add1Chip, Color.LIGHT_GRAY, Color.BLACK);
        this.customizeButton(this.add5Chip, Color.LIGHT_GRAY, Color.BLACK);
        this.customizeButton(this.add10Chip, Color.LIGHT_GRAY, Color.BLACK);
        this.customizeButton(this.add25Chip, Color.LIGHT_GRAY, Color.BLACK);
        this.customizeButton(this.add100Chip, Color.LIGHT_GRAY, Color.BLACK);
        this.dealer = new Dealer();
        this.player = new Player("James Bond", 32, "Male");
        this.player.setWallet(100.0);
        this.updateValues();
    }

    private void customizeButton(JButton var1, Color var2, Color var3) {
        var1.setBackground(var2);
        var1.setForeground(var3);
        var1.setOpaque(true);
        var1.setBorderPainted(false);
        var1.setFocusPainted(false);
        var1.setFont(new Font("Arial", 1, 14));
    }

    public void actionPerformed(ActionEvent var1) {
        String var2 = var1.getActionCommand();
        if (var2.equals("Deal")) {
            this.newGame();
        } else if (var2.equals("Hit")) {
            this.hit();
        } else if (var2.equals("Double")) {
            this.playDouble();
        } else if (var2.equals("Stand")) {
            this.stand();
        } else if (this.isBetEvent(var2)) {
            this.increaseBet(Integer.parseInt(var2));
        } else if (var2.equals("Clear")) {
            System.out.println("clear bet");
            this.clearBet();
        }

        this.updateValues();
    }

    public boolean isBetEvent(String var1) {
        return var1.equals("1") || var1.equals("5") || var1.equals("10") || var1.equals("25") || var1.equals("100");
    }

    public void newGame() {
        this.dealer.deal(this.player);
        this.table.setGameOver(false);
        this.updateValues();
        this.checkGameOver();
    }

    public void hit() {
        this.dealer.hit(this.player);
        this.updateValues();
        this.checkGameOver();
    }

    public void playDouble() {
        this.dealer.playDouble(this.player);
        this.updateValues();
        this.checkGameOver();
    }

    public void stand() {
        this.dealer.stand(this.player);
        this.updateValues();
        this.checkGameOver();
    }

    public void increaseBet(int var1) {
        this.dealer.acceptBetFrom(this.player, (double)var1 + this.player.getBet());
    }

    public void clearBet() {
        this.player.clearBet();
    }

    public void updateValues() {
        Color var1;
        if (this.getBackground().equals(Color.BLACK)) {
            var1 = Color.WHITE;
            this.dealerSays.setText("<html><p align=\"center\"><font face=\"Serif\" color=\"white\" style=\"font-size: 20pt\">" + this.dealer.says() + "</font></p></html>");
        } else {
            var1 = Color.BLACK;
            this.dealerSays.setText("<html><p align=\"center\"><font face=\"Serif\" color=\"black\" style=\"font-size: 20pt\">" + this.dealer.says() + "</font></html>");
        }

        this.doubleButton.setEnabled(!this.dealer.isGameOver() && this.dealer.canPlayerDouble(this.player));
        this.newGameButton.setEnabled(this.dealer.isGameOver() && this.player.betPlaced() && !this.player.isBankrupt());
        this.hitButton.setEnabled(!this.dealer.isGameOver());
        this.standButton.setEnabled(!this.dealer.isGameOver());
        this.clearBet.setEnabled(this.dealer.isGameOver() && this.player.betPlaced());
        this.add1Chip.setEnabled(this.dealer.isGameOver() && this.player.getWallet() >= 1.0);
        this.add5Chip.setEnabled(this.dealer.isGameOver() && this.player.getWallet() >= 5.0);
        this.add10Chip.setEnabled(this.dealer.isGameOver() && this.player.getWallet() >= 10.0);
        this.add25Chip.setEnabled(this.dealer.isGameOver() && this.player.getWallet() >= 25.0);
        this.add100Chip.setEnabled(this.dealer.isGameOver() && this.player.getWallet() >= 100.0);
        this.table.update(this.dealer.getHand(), this.player.getHand(), this.dealer.areCardsFaceUp());
        this.table.setNames(this.dealer.getName(), this.player.getName());
        this.table.setPlayerNameColor(var1);
        this.table.repaint();
        JLabel var10000 = this.cardsLeft;
        int var10001 = this.dealer.cardsLeftInPack();
        Dealer var10002 = this.dealer;
        var10000.setText("Deck: " + var10001 + "/" + 2 * 52);
        this.cardsLeft.setForeground(var1);
        if (this.player.isBankrupt()) {
            this.moreFunds();
        }

        this.currentBet.setText(Double.toString(this.player.getBet()));
        this.playerWallet.setText(Double.toString(this.player.getWallet()));
        this.currentBet.setForeground(var1);
        this.playerWallet.setForeground(var1);
    }

    private void moreFunds() {
        int var1 = JOptionPane.showConfirmDialog((Component)null, "Marshall Aid. One Hundred dollars. With the compliments of the USA.", "Out of funds", 0);
        if (var1 == 0) {
            this.player.setWallet(100.0);
            this.updateValues();
        }

    }

    public void savePlayer() {
        if (this.dealer.isGameOver()) {
            JFileChooser var1 = new JFileChooser("~");
            SimpleFileFilter var2 = new SimpleFileFilter(".ser", "(.ser) Serialised Files");
            var1.addChoosableFileFilter(var2);
            int var3 = var1.showSaveDialog(this);
            if (var3 == 0) {
                String var4 = var1.getSelectedFile().getAbsolutePath();

                try {
                    ObjectOutputStream var5 = new ObjectOutputStream(new FileOutputStream(var4));
                    var5.writeObject(this.player);
                    var5.close();
                } catch (IOException var6) {
                    System.out.println(var6);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can't save a player while a game is in progress.", "Error", 0);
        }

    }

    public void openPlayer() {
        if (this.dealer.isGameOver()) {
            JFileChooser var1 = new JFileChooser("~");
            SimpleFileFilter var2 = new SimpleFileFilter(".ser", "(.ser) Serialised Files");
            var1.addChoosableFileFilter(var2);
            int var3 = var1.showOpenDialog(this);
            if (var3 == 0) {
                String var4 = var1.getSelectedFile().getAbsolutePath();

                try {
                    ObjectInputStream var5 = new ObjectInputStream(new FileInputStream(var4));
                    Player var6 = (Player)var5.readObject();
                    var6.hand = new PlayerCardHand();
                    this.player = var6;
                    var5.close();
                    System.out.println(var6.getName());
                } catch (ClassNotFoundException var7) {
                    System.err.println(var7);
                } catch (IOException var8) {
                    System.err.println(var8);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Can't open an existing player while a game is in progress.", "Error", 0);
        }

    }

    public void updatePlayer() {
        PlayerDialog var1 = new PlayerDialog((Frame)null, "Player Details", true, this.player);
        var1.setVisible(true);
        this.player = var1.getPlayer();
    }

    private void checkGameOver() {
        if (this.dealer.isGameOver()) {
            String var1;
            if (this.player.getHand().getTotal() > 21 || this.dealer.getHand().getTotal() <= 21 && this.dealer.getHand().getTotal() > this.player.getHand().getTotal()) {
                var1 = "Você perdeu! A partida finalizou.";
            } else if (this.dealer.getHand().getTotal() <= 21 && this.player.getHand().getTotal() <= this.dealer.getHand().getTotal()) {
                var1 = "Empate! A partida finalizou.";
            } else {
                var1 = "Você ganhou! A partida finalizou.";
            }

            JOptionPane.showMessageDialog(this, var1);
            this.table.setGameOver(true);
        }

    }
}
