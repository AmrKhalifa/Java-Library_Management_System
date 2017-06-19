package view.masterPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import backEnd.DirectOperations.AddUser;
import view.masterPanel.settings.AddUserEvent;
import view.masterPanel.settings.EditUserPanel;

public class Settings extends JDialog implements ActionListener {

	private JPanel containerPanel;
	private JPanel buttonsHolder;
	private JPanel addUserPanel;
	private EditUserPanel editUsersPanel;
	private JPanel exitPanel;
	private JButton addUserButton;
	private JButton editUserButton;
	private JButton exitButton;
	CardLayout C1;

	AddUserEvent event;
	AddUser addUser;

	private final Font buttonsFont = new Font("ara Jozoor", Font.PLAIN, 20);
	private final Font buttonsFont_2 = new Font("Ara Aqeeq Bold", Font.PLAIN, 20);
	private final Font labelsFont = new Font("JF Flat", Font.PLAIN, 16);
	private final Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

	public Settings(MasterPanel parent) {

		setVisible(true);
		setTitle("إضافة مستخدم");
		setLocationRelativeTo(parent);
		setLayout();
	}

	private void setLayout() {
		setLayout(new BorderLayout());
		setSize(400, 260);
		setMinimumSize(new Dimension(400, 260));
		setMaximumSize(new Dimension(400, 260));

		containerPanel = new JPanel();
		buttonsHolder = new JPanel();
		addUserPanel = new JPanel();
		editUsersPanel = new EditUserPanel();
		exitPanel = new JPanel(); 

		C1 = new CardLayout();

		C1.addLayoutComponent(buttonsHolder, "1");
		C1.addLayoutComponent(addUserPanel, "2");
		C1.addLayoutComponent(editUsersPanel, "3");

		containerPanel.setLayout(C1);

		containerPanel.add(buttonsHolder, "1");
		containerPanel.add(addUserPanel, "2");
		containerPanel.add(editUsersPanel, "3");

		add(containerPanel, BorderLayout.CENTER);
		add(exitPanel, BorderLayout.SOUTH);

		C1.show(containerPanel, "1");

		makeContainerPanel();
		makeExitPanel();
		makeAddUserPanel();
	}

	private void makeContainerPanel() {
		buttonsHolder.setLayout(new FlowLayout(FlowLayout.CENTER));

		editUserButton = new JButton("تعديل بيانات المستخدمين");
		editUserButton.addActionListener(this);
		editUserButton.setFont(buttonsFont);

		addUserButton = new JButton("إضافة مستخدم");
		addUserButton.addActionListener(this);
		addUserButton.setPreferredSize(editUserButton.getPreferredSize());
		addUserButton.setFont(buttonsFont);

		buttonsHolder.add(addUserButton);
		buttonsHolder.add(editUserButton);

	}

	private void makeExitPanel() {
		exitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		exitButton = new JButton("خروج");
		exitButton.setFont(buttonsFont);
		exitPanel.add(exitButton);
		exitButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {

		if (ev.getSource() == addUserButton) {
			C1.show(containerPanel, "2");
			setSize(450, 450);
			setMinimumSize(new Dimension(450, 450));
			setMaximumSize(new Dimension(450, 450));
			setLocation(200, 200);
		}
		if (ev.getSource() == editUserButton) {
			C1.show(containerPanel, "3");
			setSize(600, 500);
			setMinimumSize(new Dimension(600, 500));
			setMaximumSize(new Dimension(600, 500));
			setLocation(200, 200);

		}
		if (ev.getSource() == exitButton)
			dispose();

	}

	private void makeAddUserPanel() {

		JLabel usernameLabel = new JLabel("اسم المستخدم");
		JTextField usernameField = new JTextField(20);
		JLabel passwordLabel = new JLabel("رمز المرور");
		JPasswordField passwordField = new JPasswordField(20);
		JLabel passwordConfirmLabel = new JLabel("تأكيد المرور");
		JPasswordField passwordConfirmField = new JPasswordField(20);
		JLabel typeLabel = new JLabel("النوع");
		JComboBox<String> userTypeComboBox = new JComboBox<>();
		userTypeComboBox.addItem("عادي");
		userTypeComboBox.addItem("مدير");
		JButton submitButton = new JButton("أدخل");

		submitButton.setFont(buttonsFont);
		usernameLabel.setFont(labelsFont);
		passwordLabel.setFont(labelsFont);
		passwordConfirmLabel.setFont(labelsFont);
		typeLabel.setFont(labelsFont);

		usernameField.setFont(textFieldFont);
		passwordField.setFont(textFieldFont);
		passwordConfirmField.setFont(textFieldFont);
		userTypeComboBox.setFont(textFieldFont);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (passwordField.getText().equals(passwordConfirmField.getText())
						&& !usernameField.getText().equals("") && !passwordField.getText().equals("")) {

					event = new AddUserEvent(usernameField.getText(), passwordField.getText(),
							userTypeComboBox.getSelectedItem().toString());
					try {
						addUser = new AddUser(event);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(submitButton,
								"عفواً إما كلمة السر غير صحيحة أو قمت بإدخال اسم مستخدم موجود مسبقا ً",
								"خطأ في البيانات", JOptionPane.WARNING_MESSAGE);

						e1.printStackTrace();
					} catch (Exception e1) {
					
						JOptionPane.showMessageDialog(submitButton,
								"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
								"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
					C1.show(containerPanel, "1");
				} else {
					JOptionPane.showMessageDialog(submitButton,
							"عفواً إما كلمة السر غير صحيحة أو قمت بإدخال اسم مستخدم موجود مسبقا ً", "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);

				}
			}

		});

		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		addUserPanel.setBorder(new CompoundBorder(outerBorder, innerBorder));

		addUserPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		////////////////////////////// first
		////////////////////////////// row///////////////////////////////////////////

		gc.weightx = 1;
		gc.weighty = .01;
		gc.ipady = 5;
		gc.gridx = 0;
		gc.gridy = 0;

		gc.anchor = GridBagConstraints.EAST;
		addUserPanel.add(usernameField, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		addUserPanel.add(usernameLabel, gc);

		/////////////////////////////////////// nextRow/////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;

		addUserPanel.add(passwordField, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		addUserPanel.add(passwordLabel, gc);

		/////////////////////////////////////// nextRow/////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;

		addUserPanel.add(passwordConfirmField, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		addUserPanel.add(passwordConfirmLabel, gc);
		/////////////////////////////////////// nextRow/////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;

		addUserPanel.add(userTypeComboBox, gc);
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.WEST;
		addUserPanel.add(typeLabel, gc);
		/////////////////////////////////////// nextRow/////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;

		addUserPanel.add(submitButton, gc);

	}
	
	
	private void makeEditUserPanel()
	{
		
	}

}
