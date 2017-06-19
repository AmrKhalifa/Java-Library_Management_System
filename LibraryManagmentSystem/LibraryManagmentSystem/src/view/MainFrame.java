package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import backEnd.DirectOperations.AdminEntered;
import backEnd.DirectOperations.RegularUserEntered;
import backEnd.DirectOperations.Creator.Creator;
import view.loginPanel.LoginPanel;
import view.loginPanel.SubmitButtonListener;
import view.loginPanel.UserEnteredEvent;
import view.masterPanel.MasterPanel;

public class MainFrame extends JFrame {

	private JPanel containerPanel;
	private LoginPanel loginPanel;
	private MasterPanel masterPanel;

	public MainFrame() {
		super("المكتبة العلمية");

		checkDBExistence();
		setLayout();
		makePanels();
	
	}

	public void setLayout() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listenetToExsit();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setMinimumSize(new Dimension(500, 500));
		setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.put("control", new Color(230, 240, 255));
		} catch (UnsupportedLookAndFeelException e) {
			System.out.println("couldn't set look and feel ...");
		}

	}
	
	public void listenetToExsit()
	{
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        
		            System.exit(0);
		        
		    }
		});
	}

	public void makePanels() {
		containerPanel = new JPanel();
		loginPanel = new LoginPanel();

		masterPanel = new MasterPanel();
		add(containerPanel, BorderLayout.CENTER);
		CardLayout cl = new CardLayout();
		cl.addLayoutComponent(loginPanel, "1");
		cl.addLayoutComponent(masterPanel, "2");
		containerPanel.setLayout(cl);
		masterPanel.setFrameHight(this.getHeight());

		containerPanel.add(loginPanel, "1");
		containerPanel.add(masterPanel, "2");

		cl.show(containerPanel, "1");

		masterPanel.signoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(containerPanel, "1");
			}

		});

		loginPanel.setListener(new SubmitButtonListener() {

			@Override
			public void fireAdminEntered(UserEnteredEvent event) {

				try {
					AdminEntered adminEntered = new AdminEntered(event);
					if (adminEntered.isAdmin())
						cl.show(containerPanel, "2");

					else {
						JOptionPane.showMessageDialog(loginPanel, "عفوا ً لقد قمت بإدخال بيانات خاطئة",
								"خطأ في البيانات", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(loginPanel, "عفوا ً لقد قمت بإدخال بيانات خاطئة", "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(loginPanel,
							"عفواً تعذر الاتصال بقاعدة البيانات وتحميل الجداول ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}
			}

			@Override
			public void fireRegularUserEnterd(UserEnteredEvent event) {

				try {
					RegularUserEntered regularUserEntered = new RegularUserEntered(event);
					if (regularUserEntered.isAdmin()) {

						masterPanel.hideFromRegular();
						cl.show(containerPanel, "2");

					} else {
						JOptionPane.showMessageDialog(loginPanel, "عفوا ً لقد قمت بإدخال بيانات خاطئة",
								"خطأ في البيانات", JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(loginPanel, "عفوا ً لقد قمت بإدخال بيانات خاطئة", "خطأ في البيانات",
							JOptionPane.WARNING_MESSAGE);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(loginPanel,
							"عفواً تعذر الاتصال بقاعدة البيانات  ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
							"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
					e.printStackTrace();
				}

			}

		});

	}
	
	public void checkDBExistence ()
	{
		try {
			Creator.Initialize();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(loginPanel,
					"عفواً تعذر الاتصال بقاعدة البيانات  ,الرجاء التأكد من تشغيل برنامج قاعدة البيانات أو الأتصال بالمهندس",
					"خطأ في الاتصال", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
}
