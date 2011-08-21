package poupaniquel.importers.homebank;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class HomeBankImporter {

	public HomeBank fromXml(InputStream file) throws Exception {
		XStream xstream = new XStream();
		xstream.alias("homebank", HomeBank.class);
		xstream.registerConverter(new HomeBankConverter());

		return (HomeBank) xstream.fromXML(file);
	}
	
	class HomeBankConverter implements Converter {

		@Override
		public boolean canConvert(Class type) {
			return type.getName().equals(HomeBank.class.getName());
		}

		@Override
		public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {}

		@Override
		public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
			
			Map<String, String> nodes = new TreeMap<String, String>();
			nodes.put("account", "parserAccount");
			nodes.put("pay", "parserPayee");
			nodes.put("cat", "parserCategory");
			nodes.put("ope", "parserTransaction");
			
			HomeBank homeBank = new HomeBank();
			homeBank.setVersion(reader.getAttribute("v"));

			while(reader.hasMoreChildren()) {
				reader.moveDown();
				String methodName = nodes.get(reader.getNodeName());
				invokeMethodAccordingOfNode(methodName, reader, homeBank);
				reader.moveUp();
			}
			return homeBank;
		}

		private void invokeMethodAccordingOfNode(String methodName, HierarchicalStreamReader reader, HomeBank homeBank) {
			try {
				Class classParam[] = {HierarchicalStreamReader.class, HomeBank.class};
				Method method = this.getClass().getDeclaredMethod(methodName, classParam);

				Object methodParam[] = {reader, homeBank};
				method.invoke(this.getClass().newInstance(), methodParam);
			}
			catch (Exception e) {}
		}
		
		private void parserAccount(HierarchicalStreamReader reader, HomeBank homeBank) {
			Account account = new Account();
			account.setKey(Integer.valueOf(reader.getAttribute("key")));
			account.setName(reader.getAttribute("name"));
			account.setNumber(reader.getAttribute("number"));
			account.setInitial(new BigDecimal(reader.getAttribute("initial")));
			homeBank.addAccount(account);
		}
		
		private void parserPayee(HierarchicalStreamReader reader, HomeBank homeBank) {
			Payee payer = new Payee();
			payer.setKey(Integer.valueOf(reader.getAttribute("key")));
			payer.setName(reader.getAttribute("name"));
			homeBank.addPayee(payer);
		}
		
		private void parserCategory(HierarchicalStreamReader reader, HomeBank homeBank) {
			Category category = new Category();
			category.setKey(Integer.valueOf(reader.getAttribute("key")));
			category.setParent(Integer.valueOf(reader.getAttribute("parent")));
			category.setName(reader.getAttribute("name"));
			homeBank.addCategory(category);
		}
		
		private void parserTransaction(HierarchicalStreamReader reader, HomeBank homeBank) {
			Transaction transaction = new Transaction();
			transaction.setDescription(reader.getAttribute("wording"));
			
			BigDecimal amount = new BigDecimal(reader.getAttribute("amount"));
			transaction.setAmount(amount.setScale(2, BigDecimal.ROUND_UP));
			
			GregorianCalendar calendar = convertFromJulianToGregorian(reader.getAttribute("date"));
			transaction.setDate(calendar);
			
			int categoryKey = Integer.valueOf(reader.getAttribute("category"));
			Category category = new Category();
			category.setKey(categoryKey);
			transaction.setCategory(category);
			
			int accountKey = Integer.valueOf(reader.getAttribute("account"));
			Account account = new Account();
			account.setKey(accountKey);
			transaction.setAccount(account);
			
			int payeeKey = Integer.valueOf(reader.getAttribute("payee"));
			Payee payee = new Payee();
			payee.setKey(payeeKey);
			transaction.setPayee(payee);
			
			homeBank.addTransaction(transaction);
		}

		private GregorianCalendar convertFromJulianToGregorian(String julian) {
			int julianDate = Integer.valueOf(julian);
			
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(1, Calendar.JANUARY, 1, 0, 0, 0);
			calendar.add(Calendar.DAY_OF_MONTH, julianDate + 1);
			return calendar;
		}

	}
	
}
