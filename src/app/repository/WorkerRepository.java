package app.repository;

import utils.Consultant;
import utils.Leader;
import app.elements.DatabaseElement;

public interface WorkerRepository extends Repository<DatabaseElement>{

	public long nextCID();

	public boolean addManager(Leader manager);

	public boolean addConsultant(Consultant consultant);
	
	public Consultant getConsultantByID(long cid);
	
	public Leader getManagerByID(long cid);

	public void removeAll();

}
