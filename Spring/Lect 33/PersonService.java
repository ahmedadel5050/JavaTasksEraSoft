
public class PersonService implements UserService {
	@Override
	public void save (String name) {
		System.out.println("PersonService saved " + name );
	}
	@Override
	public void update (String name) {
		System.out.println("PersonService updated " + name );
	};

    public void init() {
        System.out.println("PersonService bean is initialized");
    }


    public void destroy() {
        System.out.println("PersonService bean is destroyed");
    }
    
}
