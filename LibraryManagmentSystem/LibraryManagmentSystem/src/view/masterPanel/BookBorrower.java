package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import view.masterPanel.EventsAndListeners.BorrowBookEvent;
import view.masterPanel.EventsAndListeners.BorrowBookListener;

public class BookBorrower extends JDialog {
	private JPanel infoPanel;
	private JPanel confirmationPanel;
	private JLabel bookCodeLabel;
	private JTextField bookCodeField;
	private JLabel memberCodeLabel;
	private JTextField memberCodeField;
	private JButton submitButton;
	private JButton cancelButton;
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font buttonsFont_3 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);
	private BorrowBookEvent event;
	private BorrowBookListener listener;

	public BookBorrower(MasterPanel parent) {

		setVisible(true);
		setTitle("استلاف كتاب");
		setLocationRelativeTo(parent);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(500, 180);
		setMinimumSize(new Dimension(450, 180));
		setMaximumSize(new Dimension(500, 180));

		makeinfoPanel();
		add(infoPanel, BorderLayout.NORTH);
		makeConfirmationPanel();
		add(confirmationPanel, BorderLayout.SOUTH);
	}

	private void makeinfoPanel() {

		infoPanel = new JPanel();
		Border innerBorder = BorderFactory.createTitledBorder("");
		infoPanel.setBorder(innerBorder);

		bookCodeLabel = new JLabel("كود الكتاب");
		bookCodeLabel.setFont(labelsFont);
		bookCodeField = new JTextField(10);

		memberCodeLabel = new JLabel("كود العضو");
		memberCodeLabel.setFont(labelsFont);
		memberCodeField = new JTextField(10);

		infoPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		//////////////////////////////////// firstRow////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy = 0;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(bookCodeField, gc);
		gc.gridx = 1;
		infoPanel.add(bookCodeLabel, gc);
		//////////////////////////////////// firstRow////////////////////////////////////////
		gc.gridx = 0;
		gc.gridy++;

		gc.gridheight = 1;
		gc.ipady = 10;

		infoPanel.add(memberCodeField, gc);
		gc.gridx = 1;
		infoPanel.add(memberCodeLabel, gc);

	}

	private void makeConfirmationPanel() {
		confirmationPanel = new JPanel();
		confirmationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		submitButton = new JButton("تأكيد");
		submitButton.setFont(buttonsFont_3);

		cancelButton = new JButton("إلغاء");
		cancelButton.setFont(buttonsFont_3);

		confirmationPanel.add(submitButton);
		confirmationPanel.add(cancelButton);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				long dt = new java.util.Date().getTime();
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String rentingDate = sdf.format(dt);
				try {
					event = new BorrowBookEvent(this, bookCodeField.getText(), memberCodeField.getText(), rentingDate);
					listener.fireRentingBook(event);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(submitButton,"عفوا ً لقد قمت بإدخال بيانات خاطئة",  "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);
				} finally {
					dispose();
				}
			}

		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}

	public void setListener(BorrowBookListener listener) {
		this.listener = listener;
	}

}
