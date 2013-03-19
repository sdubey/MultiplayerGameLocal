package sfsu.edu.dao;

public interface PlantsDAO {
	public boolean save();

	public boolean update();

	public boolean findByAccountNumber(int todo);

	public boolean delete();

}
