import java.util.ArrayList;
import java.util.Scanner;

public class BookStore {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Catalog bookList = new Catalog();

		boolean finished = false;
		while (!finished) {
			System.out.println("Select (1) initialize books (2) output (3) sort (4) min and max book by "
					+ "the price (5) average price (6) insert an new kind of book by index");
			System.out.println("(7) add an new kind of book (8) delete an book by name (9) search by name,"
					+ " price or date (10) sell books (11) add copies to an exist book (12) exit ");
			int option = input.nextInt();

			if (option > 13 || option < 1) {
				System.out.println("Invalid option, please try it again!");
				option = input.nextInt();
			}
			switch (option) {
			case 1:
				bookList.addBook();
				break;
			case 2:
				bookList.Output();
				break;
			case 3:
				bookList.Sort();
				break;
			case 4:
				bookList.minAndMax();
				System.out.println(bookList.minAndMax());
				break;
			case 5:
				bookList.average();
				System.out.println("the average price is : " + bookList.average());
				break;
			case 6:
				bookList.insert();
				break;
			case 7:
				bookList.add();
				break;
			case 8:
				bookList.deleteElement();
				break;
			case 9:
				bookList.SearchBook();
				break;
			case 10:
				bookList.SearchBook();
				System.out.println("Enter the index of the book and the number of this book you want to buy ");
				int searchIndex = input.nextInt();
				int soldNumber = input.nextInt();
				if (searchIndex < 0 || searchIndex > bookList.list.size() || soldNumber < 0
						|| soldNumber > bookList.getBook(searchIndex).getNOfBook()) {
					System.out.println("Invalid search Index or invalid sold numbers, try again!");
					searchIndex = input.nextInt();
					soldNumber = input.nextInt();
				}
				if (bookList.getBook(searchIndex).getNOfBook() == 0) {
					System.out.println("This book has been sold out!");
				} else {
					System.out
							.println("You got " + soldNumber + " books of " + bookList.getBook(searchIndex).getName());
					System.out.println("You need to pay for: " + bookList.getBook(searchIndex).getPrice() * soldNumber);
					bookList.getBook(searchIndex).setNOfBook(-soldNumber);
				}
				break;
			case 11:
				bookList.SearchBook();
				System.out.println("Enter the index and the copies of this book");
				int Index = input.nextInt();
				int addNumber = input.nextInt();
				if (Index < 0 || Index > bookList.list.size() || addNumber < 0) {
					System.out.println("Invalid index or copies, try again!");
					Index = input.nextInt();
					addNumber = input.nextInt();
				}
				bookList.getBook(Index).setNOfBook(addNumber);
				bookList.Output();
				break;
			default:
				finished = true;
			}
		}
		input.close();

	}// main class Bookstore
}

class Date {
	private int year, month, day;

	public Date() {
		year = 2016;
		month = 04;
		day = 15;
	}

	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public Date(Date newDate) {
		month = newDate.month;
		day = newDate.day;
		year = newDate.year;
	}

	public void setYear(int newYear) {
		year = newYear;
	}

	public void setMonth(int newMonth) {
		month = newMonth;
	}

	public void setDay(int newDay) {
		day = newDay;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public String getDate() {
		return month + "/" + day + "/" + year;
	}

	public void output() {
		System.out.print("    Published: " + getDate());
	}
}// class Date

class Book {
	public String name;
	private String author;
	private double price;
	Date dateCreated;
	private int nOfBook;

	public Book(String name, String author, double price, Date date, int nOfBook) {
		this.name = name;
		this.author = author;
		this.price = price;
		this.dateCreated = date;
		this.nOfBook = nOfBook;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setprice(double newPrice) {
		price = newPrice;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public double getPrice() {
		return price;
	}

	public String getDate() {
		return dateCreated.getDate();
	}

	public void setNOfBook(int change) {
		nOfBook = nOfBook + change;
	}

	public int getNOfBook() {
		return nOfBook;
	}

	public void output() {
		System.out.println("name: " + name + "   author: " + author + "   price: " + price);
		dateCreated.output();
		System.out.println("   NOfBook: " + nOfBook);
	}
}// class Book

class Catalog {
	ArrayList<Book> list = new ArrayList<>();
	private int count;
	Scanner input = new Scanner(System.in);

	public Catalog() {
		count = 0;
	}

	public void addBook() {

		System.out.println("Enter the name, author, price, date and number of this book: " + count);
		String name = input.next();
		String author = input.next();
		double price = input.nextDouble();
		int month = input.nextInt();
		int day = input.nextInt();
		int year = input.nextInt();
		int nOfBook = input.nextInt();
		list.add(new Book(name, author, price, new Date(month, day, year), nOfBook));
		count++;
		return;
	}// initialize

	public void Output() {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(i + ":  ");
			list.get(i).output();
		}
	}// output

	public void Sort() {
		for (int i = (list.size() - 1); i >= 1; i--) {
			for (int j = 0; j < i; j++) {
				if (list.get(j).getPrice() > list.get(j + 1).getPrice()) {
					Book temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
	}// sort array

	public double average() {
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getPrice();
		}
		return sum / list.size();
	}// the average price

	public String minAndMax() {
		Book min = list.get(0);
		Book max = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPrice() < min.getPrice()) {
				min = list.get(i);
			}
			if (list.get(i).getPrice() > max.getPrice()) {
				max = list.get(i);
			}
		}
		return "Book with max price: " + max.getName() + " Book with min price: " + min.getName();
	}// get the largest price and smallest price

	public void insert() {
		System.out.println("Enter the index that you want to insert the object");
		int nOfIndex = input.nextInt();
		System.out.println("Enter the book name, author, price, date and number of book: ");
		String name = input.next();
		String author = input.next();
		double price = input.nextDouble();
		int month = input.nextInt();
		int day = input.nextInt();
		int year = input.nextInt();
		int nOfBook = input.nextInt();
		list.add(nOfIndex, new Book(name, author, price, new Date(month, day, year), nOfBook));

		Output();
	}// insert element

	public void add() {
		System.out.println("Enter the book's name, author, price , date and number of book that you need to add");
		String name = input.next();
		String author = input.next();
		double price = input.nextDouble();
		int month = input.nextInt();
		int day = input.nextInt();
		int year = input.nextInt();
		int nOfBook = input.nextInt();
		list.add(new Book(name, author, price, new Date(month, day, year), nOfBook));

		Output();
	}// add element

	public void deleteElement() {
		System.out.println("Enter the book name you need to delete");
		String deleteName = input.next();
		int nOfdelete = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(deleteName)) {
				nOfdelete = i;
			}
		}
		list.remove(nOfdelete);

		Output();
	}// delete an book with specific name

	public void SearchBook() {
		boolean finished = false;
		while (!finished) {
			System.out.println("Search by (1) book name (2) author (3) price (4) date (5) exit");
			int option = input.nextInt();
			if (option <= 0 || option > 5) {
				System.out.println("Invalid option, please try it again!");
				option = input.nextInt();
			}
			switch (option) {
			case 1:
				System.out.println("Enter the book name you want to search");
				boolean foundName = false;
				String name = input.next();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().equals(name)) {
						System.out.print(i + ": ");
						list.get(i).output();
						foundName = true;
					}
				}
				if (!foundName) {
					System.out.println("There is no book with the name" + name);
				}
				break;
			case 2:
				System.out.println("Enter the author name of the book you want to search");
				boolean foundAuthor = false;
				String author = input.next();
				for (int i = 0; i < list.size(); i++) {
					if (author.equals(list.get(i).getAuthor())) {
						System.out.print(i + ": ");
						list.get(i).output();
						foundAuthor = true;
					}
				}
				if (!foundAuthor) {
					System.out.println("There is no book with the author " + author);
				}
				break;
			case 3:
				System.out.println("Enter the price you want to search");
				double price = input.nextDouble();
				boolean foundPrice = false;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getPrice() == price) {
						System.out.print(i + ": ");
						list.get(i).output();
						foundPrice = true;
					}
				}
				if (!foundPrice) {
					System.out.println("There is no book with the price" + price);
				}
				break;
			case 4:
				System.out.println("Enter the date you want to search");
				boolean foundDate = false;
				int month = input.nextInt();
				int day = input.nextInt();
				int year = input.nextInt();
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).dateCreated.getMonth() == month && list.get(i).dateCreated.getDay() == day
							&& list.get(i).dateCreated.getYear() == year) {
						System.out.print(i + ": ");
						list.get(i).output();
						foundDate = true;
					}
				}
				if (!foundDate) {
					System.out.println("There is no book published on " + month + "/" + day + "/" + year);
				}
				break;
			default:
				finished = true;
			}
		}
	}

	public ArrayList<Book> getList() {
		return list;
	}

	public Book getBook(int inDex) {
		return list.get(inDex);
	}

}
