import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext  =new ClassPathXmlApplicationContext ("applicationContext.xml");
//		PersonService personService = appContext.getBean("personService", PersonService.class);
//		personService.save("SampleName");
//		personService.update("SampleNewName");

		MangerService mangerService = appContext.getBean("mangerService", MangerService.class);
		mangerService.save("SampleName");
		mangerService.update("SampleNewName");

		AccountServiceImpl accountService = appContext.getBean("accountServiceImpl", AccountServiceImpl.class);

        accountService.save("Ahmed");  
        accountService.update("NewAhmed");

        
        // prototype scope return a new instance
        PersonService person1 = appContext.getBean("personService", PersonService.class);
        person1.save("Ahmed");

        PersonService person2 = appContext.getBean("personService", PersonService.class);
        person2.save("Eslam");

        System.out.println("Are they the same instance? " + (person1 == person2));
        
//        PersonService personService = appContext.getBean("personService", PersonService.class);
//        personService.save("Ahmed");
        person1.destroy();

        appContext.close();
        

        
	}

}
