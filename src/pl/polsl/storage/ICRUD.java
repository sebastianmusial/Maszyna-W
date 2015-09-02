package pl.polsl.storage;

public interface ICRUD<T> {
	public void create(T t);
	public void read();
	public void update(T t);
	public void delete(T t);
	
}
