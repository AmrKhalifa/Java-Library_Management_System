package backEnd;

public class TestDataBaseConnection {

/*	static Connection connection;

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver found attempting connection ....");
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		final String SQLAddress = "jdbc:mysql://localhost:3306/";
		try {
			connection = DriverManager.getConnection(SQLAddress, "root", "root");
			System.out.println("connected to dataBase server ... :)");
			createDB();
			if (connection != null)
				connection.close();
			System.out.println("connection closed ....");

		} catch (SQLException e) {
			// here I'll call system to create database;
			System.err.println(e.getMessage() + "\n" + e.getSQLState());

		}

	}

	public static void createDB() {

		String SQLFindDBs = "SHOW DATABASES";

		try (Statement findStatement = connection.createStatement();
				ResultSet dataBasesSet = findStatement.executeQuery(SQLFindDBs);) {

			if (dataBasesSet.next()) {
				LinkedList<String> dataBases = new LinkedList<>();
				dataBasesSet.beforeFirst();
				System.out.println("looking for dataBase ");
				while (dataBasesSet.next()) {
					dataBases.add(dataBasesSet.getString(1));
					System.out.println("database name is :" + dataBasesSet.getString(1));
				}
				dataBasesSet.close();
				findStatement.close();
				if (!dataBases.contains("mine")) {
					System.out.println("creating dataBase");
					createNewDB();
					System.out.println("\n done creation ...");
				} else {
					System.out.println(" \n **nothing to be done , the database is found ...");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createNewDB() {

		String SQLCreateLibrary = " CREATE DATABASE mine ";
		String SQLCreateBooksTable = " CREATE TABLE `mine`.`books` (`book_id` int(10) NOT NULL,"
				+ "					  `book_title` varchar(200) NOT NULL,"
				+ "					  `book_writer` varchar(200) NOT NULL,"
				+ "					  `publishing_house` varchar(200) DEFAULT NULL,"
				+ "					  `copies_count` int(5) DEFAULT NULL,"
				+ "					  `addition_date_time` timestamp(3) NOT NULL ,"
				+ "					  PRIMARY KEY (`book_id`)," + "					  UNIQUE KEY `book_id` (`book_id`),"
				+ "					  UNIQUE KEY `book_id_2` (`book_id`)" + "					) ";

		String SQLCreateMembersTable = "CREATE TABLE `mine`.`members` "
				+ "(`member_id` int(5) NOT NULL,`member_name` varchar(50) NOT NULL,"
				+ "`study_year` varchar(10) NOT NULL,`study_field` varchar(20) NOT NULL,"
				+ " `sim_category` varchar(6) NOT NULL,`whats_app_number` varchar(10) NOT NULL,"
				+ "	 `joining_date_time` timestamp(3) NOT NULL , PRIMARY KEY (`member_id`),"
				+ "	  UNIQUE KEY `member_id` (`member_id`),	 UNIQUE KEY `member_name` (`member_name`),"
				+ "	  UNIQUE KEY `whats_app_number` (`whats_app_number`))";

		String SQLCreateUsersTable = "CREATE TABLE `mine`.`users` (" + "  `user_id` int(5) NOT NULL,"
				+ "  `username` varchar(100) NOT NULL," + "  `password` varchar(50) NOT NULL,"
				+ "  `admin` varchar(5) NOT NULL," + "  PRIMARY KEY (`user_id`),"
				+ "  UNIQUE KEY `user_id` (`user_id`)," + "  UNIQUE KEY `user_id_2` (`user_id`),"
				+ "  UNIQUE KEY `username` (`username`)" + ") ";

		String SQLCreateInitialCopiesCountTable = "CREATE TABLE `mine`.`books_initial_copies` ( "
				+ " `book_id` int(10) NOT NULL," + "  `initial_copies_count` int(5) NOT NULL,"
				+ "  PRIMARY KEY (`book_id`)," + "  UNIQUE KEY `book_id_2` (`book_id`)," + "  KEY `book_id` (`book_id`)"
				+ ") ";

		String SQLCreateMemberBookBorrowTable = "CREATE TABLE `mine`.`member_book_borrow` ("
				+ "  `operation_id` int(10) NOT NULL," + "  `member_id` int(10) NOT NULL,"
				+ "  `book_id` int(10) NOT NULL," + "  `borrow_date` timestamp(3)," + "  PRIMARY KEY (`operation_id`)"
				+ ") ";
		String SQLCreateMemberBookReturnTable = "CREATE TABLE `mine`.`member_book_returning` ("
				+ "  `operation_id` int(5) NOT NULL," + "  `member_id` int(10) NOT NULL,"
				+ "  `book_id` int(10) NOT NULL," + "  `returning_date_time` timestamp(3) NOT NULL,"
				+ "  PRIMARY KEY (`operation_id`)" + ")";

		try (Statement createStatement = connection.createStatement();

				Statement createBooksTableStatmenet = connection.createStatement();

				Statement createMembersableStatmenet = connection.createStatement();

				Statement createUsersTableStatmenet = connection.createStatement();

				Statement createInitialCopiesTableStatement = connection.createStatement();

				Statement createMemberBookBorrowTableStatmenet = connection.createStatement();

				Statement createMemberBookReturnTableStatmenet = connection.createStatement();) {

			createStatement.executeUpdate(SQLCreateLibrary);
			createBooksTableStatmenet.executeUpdate(SQLCreateBooksTable);
			createMembersableStatmenet.executeUpdate(SQLCreateMembersTable);
			createUsersTableStatmenet.executeUpdate(SQLCreateUsersTable);
			createInitialCopiesTableStatement.executeUpdate(SQLCreateInitialCopiesCountTable);
			createMemberBookBorrowTableStatmenet.executeUpdate(SQLCreateMemberBookBorrowTable);
			createMemberBookReturnTableStatmenet.executeUpdate(SQLCreateMemberBookReturnTable);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} */
	
	public static void main (String [] args)
	{
		double sum =0;
		for(int i=0;i<32;i++)
			sum = sum + Math.pow(2, i);
		System.out.println(sum);
	}
}
	
