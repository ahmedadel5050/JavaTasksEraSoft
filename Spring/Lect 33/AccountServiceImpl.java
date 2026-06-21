
public class AccountServiceImpl implements UserService{
	private final PersonService personService;
	
	 public AccountServiceImpl(PersonService personService) {
	        this.personService = personService;
	    }
	 
	 public void save(String name) {
	        personService.save(name);
	    }

	    @Override
	    public void update(String name) {
	        System.out.println("AccountServiceImpl updated " + name);
	    }
	    
}
