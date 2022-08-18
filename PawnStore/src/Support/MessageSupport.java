/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Support;

import javax.swing.JOptionPane;


@SuppressWarnings({"ClassWithoutLogger", "UtilityClassWithoutPrivateConstructor"})
public class MessageSupport {

    public static void Message(String title, String content) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ErrorMessage(String title, String content) {
        JOptionPane.showMessageDialog(null, content, title, JOptionPane.ERROR_MESSAGE);
    }

    public static int MessageConfirm(String title, String content) {
        int choose = JOptionPane.showConfirmDialog(null, content, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return choose;
    }
}
