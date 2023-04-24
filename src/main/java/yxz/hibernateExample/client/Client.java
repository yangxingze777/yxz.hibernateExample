package yxz.hibernateExample.client;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.util.CollectionUtils;

import com.yxz.hibernate.util.HibernateUtil;
import com.yxz.hibernateExample.model.AccountModel;
import com.yxz.hibernateExample.utils.Beeper;
import com.yxz.hibernateExample.utils.Encryptor;

public class Client {
	public static final String SELECT_BY_EMAIL = "FROM AccountModel where email = :emailParameter";
	
	public static void main(String[] args) throws InterruptedException, LineUnavailableException {

		// prep work
		Scanner sc = new Scanner(System.in);
		Beeper.tone(1000, 100);
		while (true) {
			menu();
			switch (sc.nextInt()) {
			case 1:
				register();
				break;
			case 2:
				login();
				System.out.println(2);
				break;
			case 3:
				Beeper.tone(1000, 100);
				forgotPassword();
				break;
			case 4:
				Beeper.tone(300, 400);
				Thread.sleep(100);
				System.out.println("Good Bye!");
				Thread.sleep(1000);
				System.out.println(new String(new char[70]).replace("\0", "\r\n")); // clear screen
				sc.close();
				System.exit(0);
				break;

			default:
				System.out.println(new String(new char[70]).replace("\0", "\r\n"));
				menu();
			}
		}

	}

	private static final void menu() throws InterruptedException, LineUnavailableException {
		Beeper.tone(1000, 100);
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("1) Register");
		Thread.sleep(300);
		System.out.println("2) Login");
		Thread.sleep(300);
		System.out.println("3) Forgot Password");
		Thread.sleep(300);
		System.out.println("4) Exit");
		Thread.sleep(300);
		System.out.println("====================================================");
	}

	private static final void register() throws InterruptedException, LineUnavailableException {

		Beeper.tone(1000, 100);
		Scanner input = new Scanner(System.in);

		AccountModel user = new AccountModel();

		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user first name");
		Thread.sleep(300);
		System.out.println("====================================================");
		user.setFirstName(input.nextLine());

		Thread.sleep(300);
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user last name");
		Thread.sleep(300);
		System.out.println("====================================================");
		user.setLastName(input.nextLine());

		Thread.sleep(300);
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user email");
		Thread.sleep(300);
		System.out.println("====================================================");
		user.setEmail(input.nextLine());

		Thread.sleep(300);
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user password");
		Thread.sleep(300);
		System.out.println("====================================================");
		user.setPassword(input.nextLine());

		Thread.sleep(300);
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user phone number");
		Thread.sleep(300);
		System.out.println("====================================================");
		user.setMobileNumber(input.nextBigInteger());

		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Integer id = (Integer) session.save(user);
		tx.commit();

		Beeper.tone(1000, 100);

		System.out.println("user generated in new id " + id);
		System.out.println("register success!");
	}

	private static final void login() throws LineUnavailableException, InterruptedException {
		Beeper.tone(1000, 100);
		Scanner input = new Scanner(System.in);
		String email;
		String password;

		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user email");
		Thread.sleep(300);
		System.out.println("====================================================");

		email = input.nextLine();

		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user password");
		Thread.sleep(300);
		System.out.println("====================================================");

		password = input.nextLine();

		
		Configuration cfg = new Configuration(); cfg.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); Session
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(SELECT_BY_EMAIL);
		query.setParameter("emailParameter", email);
		
		List<AccountModel> account = query.list();
		
		if(CollectionUtils.isEmpty(account)) {
			
			System.out.println("user doesn't exist!");
			
		}	else {
			if( Encryptor.isMatch(password , account.get(0).getPassword())) {
				
				Beeper.tone(2000, 200);
				System.out.println(account.get(0).getFirstName() + " " + account.get(0).getLastName() + " " + "logged in!");
			
			} else {
				
				System.out.println("wrong password!");
			}
		}
			
		tx.commit();
	}
	
	private static void forgotPassword() throws LineUnavailableException, InterruptedException {
		Beeper.tone(1000, 100);
		Scanner input = new Scanner(System.in);
		String email;
		String password;

		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("Input user email");
		Thread.sleep(300);
		System.out.println("====================================================");
		
		email = input.nextLine();
		
		System.out.println("====================================================");
		Thread.sleep(300);
		System.out.println("skipped email step for reset password");
		Thread.sleep(300);
		System.out.println("====================================================");
		Thread.sleep(1200);

		Configuration cfg = new Configuration(); cfg.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); Session
		session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery(SELECT_BY_EMAIL);
		query.setParameter("emailParameter", email);
		
		List<AccountModel> result = query.list();
		
		
		if(CollectionUtils.isEmpty(result)) {
			
			System.out.println("user doesn't exist!");
			
		} else {
			
			System.out.println("====================================================");
			Thread.sleep(300);
			System.out.println("Input your new password");
			Thread.sleep(300);
			System.out.println("====================================================");
			
			password = input.nextLine();
			
			AccountModel account = result.get(0);
			
			account.setPassword(password);
			if(Encryptor.isMatch(password, account.getPassword())) {
				System.out.println("your new password is " + password);
				session.saveOrUpdate(account);
			}
			
		}
		
		Beeper.tone(3000, 300);
		tx.commit();
	}
}
