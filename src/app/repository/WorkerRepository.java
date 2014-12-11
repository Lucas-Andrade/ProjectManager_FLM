package app.repository;

import utils.Consultant;
import utils.Leader;
import app.elements.DatabaseElements;

public interface WorkerRepository extends Repository<DatabaseElements>{

	public long nextCID();

	public void addManager(Leader manager);

	public void addConsultant(Consultant consultant);

}
